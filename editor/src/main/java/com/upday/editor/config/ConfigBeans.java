package com.upday.editor.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean created for coverting a domain object to entity and
 * vice-versa
 * @author Shubham Dhingra
 *
 */
@Configuration
public class ConfigBeans {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
