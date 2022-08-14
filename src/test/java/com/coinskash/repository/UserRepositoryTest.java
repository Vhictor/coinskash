package com.coinskash.repository;

import com.coinskash.model.AppUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepositoryUnderTest;

    @Test
    @Disabled
    void checkIfUsernameExist() {
//        //given
//        String username = "john@gmail.com";
//        AppUser appUser = new AppUser(null, "John", "Travolta", username, "Nigeria","09069167788","1234", true,null, new ArrayList<>());
//        userRepositoryUnderTest.save(appUser);
//
//        //when
//
//       AppUser appUserReturned = userRepositoryUnderTest.findByUsername(username);
//
//        //then
//        assertNotNull(appUserReturned);
    }
}