package com.upday.editor.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ConfigBeans {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
