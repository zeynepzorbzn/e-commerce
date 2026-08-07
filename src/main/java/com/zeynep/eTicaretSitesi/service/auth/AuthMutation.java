package com.zeynep.eTicaretSitesi.service.auth;

import com.zeynep.eTicaretSitesi.dto.auth.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import com.zeynep.eTicaretSitesi.dto.auth.RefreshInput;
import com.zeynep.eTicaretSitesi.dto.auth.RefreshResponse;

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

        @MutationMapping
        public RegisterResponse register(@Argument RegisterInput input) {return authService.register(input);}

        @MutationMapping
        public RefreshResponse refreshToken(@Argument RefreshInput input){
            return authService.refreshToken(input);
        }
    }

