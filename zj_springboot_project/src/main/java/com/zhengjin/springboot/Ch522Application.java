package com.zhengjin.springboot;

import java.util.concurrent.TimeUnit;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengjin.spring_boot_starter_hello.HelloService;
import com.zhengjin.springboot.ch6_2_3.config.AuthorSettings;

@Controller
@SpringBootApplication
public class Ch522Application {

	@Value("${book.author}")
	private String bookAuthor;

	@Value("${book.name}")
	private String bookName;

	@Autowired
	AuthorSettings authorSettings;

	@Autowired
	HelloService helloService;

	// path: /helloboot
	@RequestMapping("/")
	public @ResponseBody String defaultMsg() {
		String ret = "Hello Spring Boot";
		ret += "<br>" + String.format("book author:%s, name:%s", bookAuthor, bookName);
		ret += "<br>" + String.format("author name:%s, age:%d", authorSettings.getName(), authorSettings.getAge());
		ret += "<br>" + "Customized auto config starter: " + helloService.sayHello();
		return ret;
	}

	// path: /helloboot/index
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("bodyContent", "Spring Boot Index Page");
		return "index";
	}

	public static void main(String[] args) {

		SpringApplication.run(Ch522Application.class, args);
	}

	// 配置tomcat servlet, 使用EmbeddedServletContainerFactory代替
//	@Component
//	public static class CustomServletContainer implements EmbeddedServletContainerCustomizer {
//
//		@Override
//		public void customize(ConfigurableEmbeddedServletContainer container) {
//			container.setSessionTimeout(10, TimeUnit.MINUTES);
//		}
//	}

	// 配置tomcat servlet
	@Bean
	@Profile("dev")
	public EmbeddedServletContainerFactory devServletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.setSessionTimeout(10, TimeUnit.MINUTES);
		factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
		return factory;
	}

	@Bean
	@Profile("prod")
	public EmbeddedServletContainerFactory prodServletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");

				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};

		tomcat.setSessionTimeout(10, TimeUnit.MINUTES);
		tomcat.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
		tomcat.addAdditionalTomcatConnectors(httpConnector());
		return tomcat;
	}

	// 配置http转向https
	@Bean
	@Profile("prod")
	public Connector httpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8081);
		connector.setSecure(false);
		connector.setRedirectPort(8443);
		return connector;
	}

}
