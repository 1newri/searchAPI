package kr.co.searchAPI.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
public class WebConfiguration implements WebMvcConfigurer{
	
	@Value("${authorization.dev-yn}") private String DEV_YN;
	
	@Autowired
	private kr.co.searchAPI.interceptor.AuthenticationInterceptor authenticationInterceptor;
	
	@Bean
	public MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}
	
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowCredentials(false)
						.maxAge(3600);
			}
		};
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
		
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jsonConverter.setObjectMapper(objectMapper);
		jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
		
		return jsonConverter;
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		List<String> pathList = new ArrayList<String>();
		
		pathList.add("/api/cybercoex/**/**");
		pathList.add("/api/cybercoex/**/**/**/**");
		
		if(!"Y".equals(DEV_YN)) {
			// Always Interceptor
			registry.addInterceptor(authenticationInterceptor).addPathPatterns(pathList);
		}
	}


	
}
