package com.minseokism.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	private static final String PATH = "/errors";
	
	@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container -> {
			final ErrorPage errorPage=new ErrorPage(PATH);
			container.addErrorPages(errorPage);
		});
	}
}
