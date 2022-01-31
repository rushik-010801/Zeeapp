package com.zee.zee5app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.repository.UserRepository;

public class MainSpring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//we need to establish spring conn. / env
		//we need to initialize application container using java based confiiguraion
		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		UserRepository userRepository = applicationContext.getBean(UserRepository.class);
		UserRepository userRepository2 = applicationContext.getBean(UserRepository.class);
		System.out.println(userRepository.equals(userRepository2));
		applicationContext.close();
	}

}
