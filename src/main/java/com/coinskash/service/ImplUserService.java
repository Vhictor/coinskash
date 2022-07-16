package com.coinskash.service;

import com.coinskash.model.AppUser;
import com.coinskash.model.Roles;
import com.coinskash.repository.RolesRepository;
import com.coinskash.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ImplUserService implements UserService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AppUser saveUser(AppUser appUser) {
        log.info("Saving user {} in the database " + appUser.getUsername());
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
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}
