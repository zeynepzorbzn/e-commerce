package com.zeynep.eTicaretSitesi.service.auth;

import com.zeynep.eTicaretSitesi.dto.auth.LoginInput;
import com.zeynep.eTicaretSitesi.dto.auth.LoginResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

    @Controller
    public class AuthMutation {

        private final AuthService authService;

        public AuthMutation(AuthService authService) {
            this.authService = authService;
        }

        @MutationMapping
        public LoginResponse login(@Argument LoginInput input) {
            return authService.login(input);
        }

    }

