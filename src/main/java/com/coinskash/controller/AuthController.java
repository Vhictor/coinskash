package com.coinskash.controller;


import com.coinskash.model.AppUser;
import com.coinskash.model.FormToAddRole;
import com.coinskash.model.ResponseDataFormat;
import com.coinskash.model.Roles;
import com.coinskash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

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
        }
        return new ResponseEntity<>(new ResponseDataFormat("success", "Account has been created"), HttpStatus.OK);
    }


    @PostMapping("/save/role")
    private ResponseEntity<ResponseDataFormat> saveUserRole(@RequestBody Roles roles) {
        if (roles != null) {
            userService.saveRole(roles);
        }
        return new ResponseEntity<>(new ResponseDataFormat("success", "Account has been created"), HttpStatus.OK);
    }


    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody FormToAddRole form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }








}
