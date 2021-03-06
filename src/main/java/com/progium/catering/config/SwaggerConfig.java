package com.progium.catering.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.paths.RelativeSwaggerPathProvider;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;


@Configuration
@EnableSwagger
public class SwaggerConfig implements ServletContextAware{
	
	private SpringSwaggerConfig springSwaggerConfig;
	private ServletContext servletContext;
	 
	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}
	@Bean
	// Don't forget the @Bean annotation
	public SwaggerSpringMvcPlugin customImplementation() {
		RelativeSwaggerPathProvider relativeSwaggerPathProvider = new RelativeSwaggerPathProvider(servletContext);
        relativeSwaggerPathProvider.setApiResourcePrefix("catering");
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
        .pathProvider(relativeSwaggerPathProvider);
        
	}
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Catering API", "API for Catering",
				"Catering API terms of service", "Catering email",
				"Catering API Licence Type", "Catering API License URL");
		return apiInfo;
	}
	
	@Override
	 public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
