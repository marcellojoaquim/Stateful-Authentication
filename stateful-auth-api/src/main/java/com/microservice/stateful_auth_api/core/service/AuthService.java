package com.microservice.stateful_auth_api.core.service;

import com.microservice.stateful_auth_api.core.dto.AuthRequest;
import com.microservice.stateful_auth_api.core.dto.AuthUserResponse;
import com.microservice.stateful_auth_api.core.dto.TokenDTO;
import com.microservice.stateful_auth_api.core.model.User;
import com.microservice.stateful_auth_api.core.repository.UserRepository;
import com.microservice.stateful_auth_api.infra.exception.AuthenticationException;
import com.microservice.stateful_auth_api.infra.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public TokenDTO login(AuthRequest request) {
        var user = findByUsername(request.username());
        var accessToken = tokenService.createToken(user.getUserName());
        validatePassword(request.password(), user.getPassword());
        return  new TokenDTO(accessToken);
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        if(isEmpty(rawPassword)) {
            throw  new ValidationException("The password must be informed.");
        }
        if(!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new ValidationException("The password is incorrect.");
        }

    }

    public TokenDTO validateToken(String accessToken) {
        validateExistingToken(accessToken);
        var valid = tokenService.validateAccessToken(accessToken);
        if(valid) {
            return new TokenDTO(accessToken);
        }
        throw new AuthenticationException("Invalid token!");
    }

    public AuthUserResponse getAuthenticatedUser(String accessToken) {
        var tokenData = tokenService.getTokenData(accessToken);
        var user = findByUsername(tokenData.username());
        return new AuthUserResponse(user.getId(), user.getUserName());
    }

    public void logout(String accessToken) {
        tokenService.deleteRedisToken(accessToken);
    }

    private User findByUsername(String username) {
        return repository.findByUserName(username)
                .orElseThrow(() -> new ValidationException("User not found."));
    }

    private void validateExistingToken(String accessToken) {
        if(isEmpty(accessToken)) {
            throw new ValidationException("The accessToken must be informec.");
        }
    }
}
