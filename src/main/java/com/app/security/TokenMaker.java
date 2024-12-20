package com.app.security;

import java.time.Duration;

public interface TokenMaker {

    String createToken(String username, Duration duration);

    boolean verifyToken(String token);

}
