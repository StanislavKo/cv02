package com.cv.eagle6.depuser.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.cv.eagle6.depuser.controller,com.cv.eagle6.depuser.component,com.cv.eagle6.depuser.config,com.cv.eagle6.depuser.interceptor")
@EnableJpaRepositories("com.cv.eagle6.depuser.db.repository")
@EntityScan("com.cv.eagle6.depuser.db.entity")
@PropertySource("classpath:persistence.properties")
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		for (String name : applicationContext.getBeanDefinitionNames()) {
			logger.debug("bean name={}", name);
		}
	}

}