package com.microservice.stateful_any_api.core.service;

import com.microservice.stateful_any_api.core.cliente.TokenClient;
import com.microservice.stateful_any_api.core.dto.AuthUserResponse;
import com.microservice.stateful_any_api.core.dto.TokenDTO;
import com.microservice.stateful_any_api.infra.exception.AuthenticationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TokenService {

    private final TokenClient tokenClient;

    public void validateToken(String token) {
        try {
            log.info("Sending request for token validation {}", token);
            TokenDTO tokenDTO = tokenClient.validateToken(token);
            log.info("Token is valid: {}", tokenDTO.accessToken());
        } catch (Exception e) {
            throw new AuthenticationException("Auth error: "+e.getMessage());
        }
    }

    public AuthUserResponse getAuthenticatedUser(String token) {
        try {
            log.info("Sending request for auth user: {}", token);
            AuthUserResponse authenticatedUser = tokenClient.getAuthenticatedUser(token);
            log.info("Auth user found: {} and Token {}", authenticatedUser.toString(), token);
            return authenticatedUser;
        } catch (Exception e) {
            throw new AuthenticationException("Auth to get authenticated user: "+e.getMessage());
        }
    }
}
