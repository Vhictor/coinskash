package com.coinskash.controller;


import com.coinskash.exception.DataNotFoundException;
import com.coinskash.exception.InvalidDataException;
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
      if (newUserData != null ) {
            userService.saveUser(newUserData);
            userService.addRoleToUser(newUserData.getUsername(), "ROLE_USER");
            userService.createUserTokenAndSendVerificationMail(newUserData);
       }
        return new ResponseEntity<>(new ResponseDataFormat(true,"Account has been created", HttpStatus.CREATED), HttpStatus.CREATED);
    }


    @PostMapping("/register/verify")
    private ResponseEntity<ResponseDataFormat> verifyUser(@RequestParam(value = "token") String token){
           log.info("Hitting this endpoint now with token {} ",token);
           boolean verifyUser = userService.verifyUser(token);
           if (verifyUser){
               return new ResponseEntity<>(new ResponseDataFormat(true, "User has been verified", HttpStatus.OK), HttpStatus.OK);
           }else
            throw new InvalidTokenException("Invalid token");
    }

    @PostMapping("/save/role")
    private ResponseEntity<ResponseDataFormat> saveUserRole(@RequestBody Roles roles) {
        if (roles != null) {
            userService.saveRole(roles);
        }
        return new ResponseEntity<>(new ResponseDataFormat(true,"Role has been created", HttpStatus.OK), HttpStatus.OK);
    }


    //Password reset token to change password

    @PostMapping("/reset-password/token")
    private ResponseEntity<Object> generateUserToken (@RequestParam(value = "email") String email){
        AppUser appUser =  userService.getUser(email);
        String generatedToken =  userService.createUserTokenAndSendVerificationMail(appUser);

        return new ResponseEntity<>(generatedToken, HttpStatus.OK);
    }

    //To finally change password


    @PostMapping("/change-password")
    private ResponseEntity<ResponseDataFormat>  resetUserPassword(@RequestBody ResetPasswordData resetPasswordData){
            log.info("Hitting this endpoint now with token {} ",resetPasswordData.getToken());
            boolean changePassword = userService.verifyUserTokenAndResetPassword(resetPasswordData.getToken(),resetPasswordData.getPassword());
        if (changePassword){
            return new ResponseEntity<>(new ResponseDataFormat(true, "Password changed successfully", HttpStatus.OK), HttpStatus.OK);
        }else
            throw new InvalidTokenException("Invalid/Expired Token");
    }



//
//    private String createTokenAndSendMail(AppUser appUser){
//        VerificationToken verificationToken = tokenService.createVerificationToken();
//        verificationToken.setUser(appUser);
//        log.info("Token is {} ", verificationToken.getToken());
//        tokenRepository.save(verificationToken);
//        String verificationUrl = tokenService.buildVerificationUrl(verificationToken);
//        log.info("The URL is  {} " +verificationUrl);
//        emailService.sendSimpleMail(appUser.getUsername(),verificationUrl,"Your Verification URL");
//        return verificationToken.getToken();
//    }

}
