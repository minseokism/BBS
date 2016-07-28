package com.minseokism.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

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
	
	@Bean
    public FilterRegistrationBean xssEscapeServletFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new XssEscapeServletFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
