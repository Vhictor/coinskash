package com.coinskash.service;

import com.coinskash.exception.InvalidDataException;
import com.coinskash.exception.InvalidTokenException;
import com.coinskash.model.AppUser;
import com.coinskash.model.Roles;
import com.coinskash.model.VerificationToken;
import com.coinskash.repository.RolesRepository;
import com.coinskash.repository.UserRepository;
import com.coinskash.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ImplUserService implements UserService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificationTokenService tokenService;



    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AppUser saveUser(AppUser appUser) {
        log.info("Saving user {} in the database " + appUser.getUsername());
        if (appUser.getUsername().isEmpty() || appUser.getUsername()==null){
            throw new InvalidDataException("Email is required");
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userRepository.save(appUser);
    }

    @Override
    public Roles saveRole(Roles roles) {
        log.info("Saving role {} in the database " + roles.getRole());
        return rolesRepository.save(roles);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
    AppUser appUser = userRepository.findByUsername(username);
    Roles roles = rolesRepository.findByRole(roleName);
    log.info("The role {} added based on the role name {} is" +roles.getRole(), roleName);
    if (appUser!=null){
        appUser.getRoles().add(roles);
    }else {
        log.info("The user value is null");
    }
    }

    @Override
    public AppUser getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void changeUserPassword(AppUser appUser, String newPassword) {
        appUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(appUser);
    }

    @Override
    public boolean verifyUser(String token) throws InvalidTokenException {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (Objects.isNull(verificationToken)){
            throw new InvalidTokenException("Token is not valid");
        }
        Optional<AppUser> optionalAppUser =  userRepository.findById(verificationToken.getUser().getId());
        if (optionalAppUser.isEmpty()){
        log.info("Optional data is empty");
        return false;
        }
        AppUser appUser = optionalAppUser.get();
        appUser.setVerified(true);
        userRepository.save(appUser);
        verificationTokenRepository.removeByToken(token);
        return true;
    }

    @Override
    public boolean verifyUserTokenAndResetPassword(String token, String newPassword) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (Objects.isNull(verificationToken)){
            throw new InvalidTokenException("Token is not valid");
        }
        Optional<AppUser> optionalAppUser =  userRepository.findById(verificationToken.getUser().getId());
        if (optionalAppUser.isEmpty()){
            log.info("Optional data is empty");
            return false;
        }
        AppUser appUser = optionalAppUser.get();
        log.info("The name of the user is {} new password is {} ",appUser.getFirstName(), newPassword);
        appUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(appUser);
        verificationTokenRepository.removeByToken(token);
        return true;
    }

    @Override
    public String createUserTokenAndSendVerificationMail(AppUser appUser) {
        VerificationToken verificationToken = tokenService.createVerificationToken();
        verificationToken.setUser(appUser);
        log.info("Token is {} ", verificationToken.getToken());
        verificationTokenRepository.save(verificationToken);
        String verificationUrl = tokenService.buildVerificationUrl(verificationToken);
        log.info("The URL is  {} " +verificationUrl);
        emailService.sendSimpleMail(appUser.getUsername(),verificationUrl,"Your Verification URL");
        return verificationToken.getToken();

    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}
