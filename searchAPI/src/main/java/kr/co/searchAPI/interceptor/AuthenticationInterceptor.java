package kr.co.searchAPI.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.searchAPI.common.auth.dto.AuthDTO;
import kr.co.searchAPI.common.auth.service.CommonAuthService;
import kr.co.searchAPI.exception.UnauthorizedException;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	
	
	@Autowired
	private CommonAuthService commonAuthService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// header로부터 authorization 토큰을 읽어옴
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(authorization  != null) {
			
			String[] split = authorization.split(" ");
			
			String type = split[0];
			String token = split[1];
			
			AuthDTO authVO = null;
			
			if("Bearer".equalsIgnoreCase(type)) {
				//인증수행
				authVO = commonAuthService.bearerToken(token);
			}else if("Basic".equalsIgnoreCase(type)) {
				// 인증수행
				authVO = commonAuthService.basicAuth(token);
			}else{
				throw new UnauthorizedException("Unsupported type: " + type);
			}
			
			// 결과를 request attribute로 넘겨준다.
			request.setAttribute("authVO", authVO);
			
		}else{
			request.setAttribute("errorMsg", "인증오류");
			request.setAttribute("msg", "인증정보를 확인할 수 없습니다.");
			throw new UnauthorizedException("invalid_client : Client authentication failed.");
		}
	
		
		return super.preHandle(request, response, handler);
	}

}
