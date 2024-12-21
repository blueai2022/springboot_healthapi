package com.app.security;

import java.time.Duration;
import java.util.Optional;

import com.app.dto.TokenPayload;

public interface TokenMaker {

    String createToken(String username, Duration duration);

    Optional<TokenPayload> verifyToken(String token);

}
