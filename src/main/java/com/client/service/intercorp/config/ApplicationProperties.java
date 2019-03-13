package com.client.service.intercorp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class ApplicationProperties {

	
	@Value("${info.project.name}")
	private String name;
	
	@Value("${info.project.description}")
	private String description;
	
	@Value("${info.project.version}")
	private String version;
}
