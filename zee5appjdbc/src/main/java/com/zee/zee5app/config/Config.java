package com.zee.zee5app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // Used to mark config classes/class

@ComponentScan("com.zee.zee5app")
public class Config {
	//config : DB related, reading prop file commonly used beans(passwordencoders)
}
