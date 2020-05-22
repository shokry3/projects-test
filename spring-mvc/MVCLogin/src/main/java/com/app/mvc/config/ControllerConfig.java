package com.app.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc //TODO :: Need to know more about WebMvcConfigurerAdapter & @EnableWebMvc
@Import(value={BeenConfig.class})
@ComponentScan({"com.app.mvc.controller"})
public class ControllerConfig  extends WebMvcConfigurerAdapter{
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/views/pages/");
		vr.setSuffix(".jsp");
		
		return vr;
	}

}
