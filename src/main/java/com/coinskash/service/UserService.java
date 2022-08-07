package com.coinskash.service;

import com.coinskash.exception.InvalidTokenException;
import com.coinskash.model.AppUser;
import com.coinskash.model.Roles;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser appUser);
    Roles saveRole(Roles roles);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    void changeUserPassword(AppUser appUser, String newPassword);
    boolean verifyUser(String token) throws InvalidTokenException;
    boolean verifyUserTokenAndResetPassword(String token, String newPassword);
    String createUserTokenAndSendVerificationMail(AppUser appUser);
    List<AppUser> getAllUsers();
}
