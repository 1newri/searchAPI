package kr.co.searchAPI.common.auth.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import kr.co.searchAPI.common.auth.dto.AuthDTO;
import kr.co.searchAPI.common.auth.service.CommonAuthService;
import kr.co.searchAPI.exception.UnauthorizedException;


@Service
public class CommonAuthServiceImpl implements CommonAuthService{
	
	@Value("${authorization.username}") private String USER_NAME;
	@Value("${authorization.password}") private String PASSWORD;
	@Value("${authorization.access-token}") private String ACCESS_TOKEN;


	@Override
	public AuthDTO bearerToken(String accessToken) {
		
		try {
			
			AuthDTO authVO = null;
			
			if( ACCESS_TOKEN.equals(accessToken)) {
				authVO = AuthDTO.builder()
						.accessToken(accessToken)
						.build();
			}
			
			if(authVO == null) {
				throw new UnauthorizedException("Invalid credentials");
			}else {
				return authVO;
			}
				
		} catch (Exception e) {
			throw new UnauthorizedException("Invalid credentials");
		}
		
	}
	
	@Override
	public AuthDTO basicAuth(String credential) {
		
		try {
			
			// credential을 디코딩하여 username과 password를 분리
			String decoded = new String(Base64Utils.decodeFromString(credential));
			String[] usernameAndPassword = decoded.split(":");
			
			String username = usernameAndPassword[0];
			String password = usernameAndPassword[1];
			
			AuthDTO authVO = null;
			
			if(USER_NAME.equals(username) && PASSWORD.equals(password)) {
				authVO = AuthDTO.builder()
						.username(username)
						.password(password)
						.build();
			}
			
			if(authVO == null) {
				throw new UnauthorizedException("Invalid credentials");
			}else {
				return authVO;
			}
				
		} catch (Exception e) {
			throw new UnauthorizedException("Invalid credentials");
		}
		
	}
}
