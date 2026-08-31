package com.zeynep.eTicaretSitesi.service.auth;

import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.core.enums.RoleName;
import com.zeynep.eTicaretSitesi.core.exceptions.InvalidTokenException;
import com.zeynep.eTicaretSitesi.core.exceptions.UserAlreadyExistsException;
import com.zeynep.eTicaretSitesi.core.security.JwtService;
import com.zeynep.eTicaretSitesi.dto.auth.*;
import com.zeynep.eTicaretSitesi.logic.role.RoleLogic;
import com.zeynep.eTicaretSitesi.logic.user.UserLogic;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserLogic userLogic;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RoleLogic roleLogic;

    public AuthService(UserLogic userLogic, RoleLogic roleLogic, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userLogic = userLogic;
        this.roleLogic = roleLogic;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    public LoginResponse login(LoginInput input) {
        User user = userLogic.findByEmail(input.getEmail()).orElseThrow(() -> new BadCredentialsException("Kullanıcı bulunamadı"));
        if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Email veya şifre hatalı.");}
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new LoginResponse(accessToken, refreshToken, user.getRole().getName());
    }

    public RegisterResponse register(RegisterInput input) {
        userLogic.findByEmail(input.getEmail()).ifPresent(user -> { throw new  UserAlreadyExistsException("Bu mail kullanılmaktadır.");});
        User user = new User();

        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPhoneNumber(input.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setBirthDate(input.getBirthDate());

        user.setRole(roleLogic.findByName(RoleName.USER));
        User savedUser = userLogic.save(user);
        String accessToken = jwtService.generateAccessToken(savedUser);
        String refreshToken = jwtService.generateRefreshToken(savedUser);
        return new RegisterResponse( savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail(),
                savedUser.getPhoneNumber(), savedUser.getRole().getName(), accessToken, refreshToken);
    }

    public RefreshResponse refreshToken(RefreshInput input){

        String refreshToken = input.getRefreshToken();

        if (!jwtService.isRefreshToken(refreshToken)) {
            throw new InvalidTokenException("Geçersiz refresh token.");
        }
        String email = jwtService.extractUsername(refreshToken);
        User user = userLogic.findByEmail(email).orElseThrow(() -> new BadCredentialsException("Kullanıcı bulıunamadı."));

        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new InvalidTokenException("Refresh token geçersiz.");}

        String accessToken= jwtService.generateAccessToken(user);
        return new RefreshResponse(accessToken);
    }
}
// name, surname, email, phonenumber, password

// OPTIONAL -> boş dönebilir veya objeyi dönebilir. Ama dönüş tipi optional olduğu için döndüğü veriye hemen erişemezsin.
// Optional.get -> ya null döner ya objeyi döner.
// .orelse() -> varsa bind et yoksa başka bir veri setle.
// .orelsethrow() -> varsa bir şey yoksa exception
// .ifpresent() -> varsa bir şey
// .orelseget() -> varsa bind et yoksa bambaşka bir şey yap.