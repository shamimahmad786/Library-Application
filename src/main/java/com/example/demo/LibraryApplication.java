package com.example.demo;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableConfigurationProperties
@SpringBootApplication
public class LibraryApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
	//	Logger.getLogger("org.apache").setLevel(Level.OFF);  // PUT THE LEVEL ACC TO
		SpringApplication.run(LibraryApplication.class, args);
	}
	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelmapper =new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelmapper;
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LibraryApplication.class);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {

	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**")
	                    .allowedOrigins("https://demopgi.udiseplus.gov.in",
	                            "http://localhost:8182",
	                            "http://10.25.26.251:8182",
	                            "http://10.25.26.251:8086",
	                            "http://localhost:8086",
	                            "http://localhost:1000"
	                            );
	        }
	    };
	   }
	
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	
}
