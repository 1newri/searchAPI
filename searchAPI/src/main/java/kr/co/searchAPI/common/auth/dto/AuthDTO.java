package kr.co.searchAPI.common.auth.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ConfigurationProperties(prefix="authorization")
public class AuthDTO {
		
	private String username;
	private String password;
	private String accessToken;
	private String devYn;
	
}
