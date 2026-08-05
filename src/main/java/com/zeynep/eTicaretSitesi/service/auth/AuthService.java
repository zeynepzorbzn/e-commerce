package com.zeynep.eTicaretSitesi.service.auth;

import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.core.security.JwtService;
import com.zeynep.eTicaretSitesi.dto.auth.LoginInput;
import com.zeynep.eTicaretSitesi.dto.auth.LoginResponse;
import com.zeynep.eTicaretSitesi.repo.user.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    public LoginResponse login(LoginInput input) {
        User user = userRepository.findByEmail(input.getEmail()).orElseThrow(() -> new BadCredentialsException("Kullanıcı bulunamadı"));
        if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Email veya şifre hatalı.");}
        String token = jwtService.generateToken(user);
        return new LoginResponse(token);
    }
}
