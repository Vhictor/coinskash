package com.coinskash.service;

import com.coinskash.model.AppUser;
import com.coinskash.model.Roles;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser appUser);
    Roles saveRole(Roles roles);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getAllUsers();
}
