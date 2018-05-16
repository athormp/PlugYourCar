package com.plugyourcar.backend;

import javax.ws.rs.core.Application;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
@SpringBootApplication
public class PlugyourcarBackendApplication {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	    return builder.build();
	 }
//
//	@Bean
//	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//		return args -> {
//			log.debug("Llamada REST");
//			PuntoCargaDTO[] puntosCargaDTO = restTemplate.getForObject("http://api.openchargemap.io/v2/poi/?output=json&countrycode=ES&maxresults=1&compact=true&verbose=false&includecomments=false&opendata=true", PuntoCargaDTO[].class);
//			log.debug(puntosCargaDTO.toString());
//		};
//	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(PlugyourcarBackendApplication.class, args);
	}
}
