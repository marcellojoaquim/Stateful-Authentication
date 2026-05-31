package com.microservice.stateful_any_api.core.service;

import com.microservice.stateful_any_api.core.dto.AnyResponse;
import com.microservice.stateful_any_api.core.dto.AuthUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnyService {

    private final TokenService tokenService;

    public AnyResponse getData(String accessToken) {
        tokenService.validateToken(accessToken);
        AuthUserResponse authenticatedUser = tokenService.getAuthenticatedUser(accessToken);
        HttpStatus ok = HttpStatus.OK;
        return new AnyResponse(ok.name(), ok.value(), authenticatedUser);
    }
}
