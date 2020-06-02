package com.zhengjin.springboot.ch.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

@Aspect
@Component
public class LogAopHandler {

	private static final Logger logger = LoggerFactory.getLogger(LogAopHandler.class);

	@Pointcut("@annotation(com.zhengjin.springboot.ch.aop.EagleEye)")
	public void eagleEye() {
	}

	@Around("eagleEye()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		Signature signature = pjp.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		EagleEye eagleEye = method.getDeclaredAnnotation(EagleEye.class);
		String desc = eagleEye.desc();

		logger.info("==================== start ====================");
		logger.info("# interface desc: {}", desc);
		logger.info("# request url: {}", request.getRequestURL().toString());
		logger.info("# request type: {}", request.getMethod());
		logger.info("# request method: {} {}", signature.getDeclaringTypeName(), signature.getName());
		logger.info("# request IP: {}", request.getRemoteAddr());
		logger.info("#");

		Object[] args = pjp.getArgs();
		if (args != null && args.length > 0) {
			if ("application/json".equals(request.getContentType())) {
				logger.info("# request parameters: {}", JSON.toJSONString(args));
			} else {
				logger.info("# request parameters: {}", Arrays.toString(args));
			}
		}

		long begin = System.currentTimeMillis();
		Object result = pjp.proceed();
		long end = System.currentTimeMillis();

		logger.info("# cost time: {} ms", (end - begin));
//		logger.info("# response data: {}", JSON.toJSONString(result));
		logger.info("# response data: {}", result.toString());
		logger.info("====================  end  ====================");

		return result;
	}

}
