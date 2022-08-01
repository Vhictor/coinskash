package com.coinskash.controller;


import com.coinskash.exception.InvalidTokenException;
import com.coinskash.model.*;
import com.coinskash.repository.VerificationTokenRepository;
import com.coinskash.service.EmailService;
import com.coinskash.service.UserService;
import com.coinskash.service.VerificationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/api")
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationTokenService tokenService;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @Value("${site.base.url}")
    private String baseURL;

    @GetMapping("/")
    private String welcome() {
        return "Welcome to Coinskash";
    }

    @GetMapping("/users")
    private ResponseEntity<List<AppUser>> getAllUsers () {
        List<AppUser> userList = userService.getAllUsers();
        return ResponseEntity.ok().body(userList);
    }

    @PostMapping("/register")
    private ResponseEntity<ResponseDataFormat> registerNewUser(@Valid @RequestBody AppUser newUserData) {

        if (newUserData != null) {
            userService.saveUser(newUserData);
            userService.addRoleToUser(newUserData.getUsername(), "ROLE_USER");
            createUserTokenAndSendVerificationMail(newUserData);
        }
        return new ResponseEntity<>(new ResponseDataFormat("success", "Account has been created"), HttpStatus.OK);
    }

    private void createUserTokenAndSendVerificationMail(AppUser newUserData) {
        VerificationToken verificationToken = tokenService.createVerificationToken();
        verificationToken.setUser(newUserData);
        log.info("Token is {} ", verificationToken.getToken());
        tokenRepository.save(verificationToken);
        String verificationUrl = buildVerificationUrl(verificationToken);
        log.info("The URL is  {} " +verificationUrl);
        emailService.sendSimpleMail(newUserData.getUsername(),verificationUrl,"Your Verification URL");


    }

    private String buildVerificationUrl(VerificationToken verificationToken) {
        String url = UriComponentsBuilder.fromHttpUrl(baseURL).path("/api/register/verify")
                .queryParam("token",verificationToken.getToken()).toUriString();
        return url;
    }

    @PostMapping("/register/verify")
    private String verifyUser(@RequestParam(value = "token") String token){
       try{
           log.info("Hitting this endpoint now with token {} ",token);
           userService.verifyUser(token);
       }catch (InvalidTokenException exception){
           log.info("The problem is {} "+exception.getMessage());
       }

        return "Success";
    }

    @PostMapping("/save/role")
    private ResponseEntity<ResponseDataFormat> saveUserRole(@RequestBody Roles roles) {
        if (roles != null) {
            userService.saveRole(roles);
        }
        return new ResponseEntity<>(new ResponseDataFormat("success", "Account has been created"), HttpStatus.OK);
    }


    @PostMapping("/generate-reset-password-token")
    private String generateUserToken (@RequestParam(value = "email") String email){
        AppUser appUser =  userService.getUser(email);
        if (Objects.isNull(appUser)){
            return "Email address is not registered";
        }else {
            createUserTokenAndSendVerificationMail(appUser);
        }

        return "Success";
    }


    @PostMapping("/change-password")
    private String resetUserPassword(@RequestParam ResetPasswordData resetPasswordData){
        try{
            log.info("Hitting this endpoint now with token {} ",resetPasswordData.getToken());
            userService.verifyUserTokenAndResetPassword(resetPasswordData.getToken(),resetPasswordData.getNewPassword());
        }catch (InvalidTokenException exception){
            log.info("The problem is {} "+exception.getMessage());
        }

        return "Success";
    }










}
