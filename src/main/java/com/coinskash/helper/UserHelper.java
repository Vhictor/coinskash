package com.coinskash.helper;

import com.coinskash.model.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {
    public Long getUserId() {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = appUser.getId();
        return userId;
    }
}
