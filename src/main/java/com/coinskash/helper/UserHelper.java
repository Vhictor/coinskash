package com.coinskash.helper;

import com.coinskash.exception.GlobalRequestException;
import com.coinskash.model.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {
    public Long getUserId() {
        try {
            AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return appUser.getId();
        }catch (Exception ex){
            throw new GlobalRequestException("403","unauthorized access", HttpStatus.FORBIDDEN);
        }

    }
}
