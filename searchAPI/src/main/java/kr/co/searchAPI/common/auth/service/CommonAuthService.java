package kr.co.searchAPI.common.auth.service;

import kr.co.searchAPI.common.auth.dto.AuthDTO;

public interface CommonAuthService {

	AuthDTO basicAuth(String token);
	AuthDTO bearerToken(String token);

}
