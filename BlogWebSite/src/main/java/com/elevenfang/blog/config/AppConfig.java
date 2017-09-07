package com.elevenfang.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.guice.annotation.EnableGuiceModules;

@Configuration
@ComponentScan(basePackages = "com.elevenfang.blog")
@EnableGuiceModules
public class AppConfig {

	@Bean
	public BlogModule blogModule() {
		return new BlogModule();
	}
}
