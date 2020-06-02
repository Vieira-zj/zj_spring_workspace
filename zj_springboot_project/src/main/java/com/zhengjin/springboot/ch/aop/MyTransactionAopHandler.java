package com.zhengjin.springboot.ch.aop;

import java.lang.reflect.Method;
import java.sql.SQLException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhengjin.springboot.utils.DataSourceConnectHolder;

@Aspect
@Component
public class MyTransactionAopHandler {

	@Autowired
	DataSourceConnectHolder connectHolder;
	Class<? extends Throwable>[] es;

	// 拦截有MyTransaction注解的方法
	@Pointcut("@annotation(com.zhengjin.springboot.ch.aop.MyTransaction)")
	public void Transaction() {
	}

	@Around("Transaction()")
	public Object TransactionProceed(ProceedingJoinPoint proceed) throws Throwable {
		Object result = null;
		Signature signature = proceed.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		if (method == null) {
			return result;
		}

		MyTransaction transaction = method.getAnnotation(MyTransaction.class);
		if (transaction != null) {
			es = transaction.rollbackFor();
		}

		try {
			result = proceed.proceed();
		} catch (Throwable throwable) {
			this.completeTransactionAfterThrowing(throwable);
			throw throwable;
		}
		this.doCommit();
		return result;
	}

	/**
	 * 异常处理，捕获的异常是目标异常或者其子类，就进行回滚，否则就提交事务
	 */
	private void completeTransactionAfterThrowing(Throwable throwable) {
		if (this.es != null) {
			for (Class<? extends Throwable> e : es) {
				if (e.isAssignableFrom(throwable.getClass())) {
					this.doRollBack();
				}
			}
		}
		this.doCommit();
	}

	/**
	 * 执行提交，最后关闭连接和清理线程绑定
	 */
	private void doCommit() {
		try {
			this.connectHolder.getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.connectHolder.cleanHolder();
		}
	}

	/**
	 * 执行回滚，最后关闭连接和清理线程绑定
	 */
	private void doRollBack() {
		try {
			this.connectHolder.getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.connectHolder.cleanHolder();
		}
	}

}
