package com.projeto.changebookusers.config.security;

public class SecurityConstraints {

    static final String SECRET = "ChangeBookAuth";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SING_UP_URL = "/api/change-book/v1/users/sign-up";
    static final long EXPIRARION_TIME = 84600000L;

}
