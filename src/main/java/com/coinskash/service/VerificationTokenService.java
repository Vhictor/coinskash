package com.coinskash.service;

import com.coinskash.model.AppUser;
import com.coinskash.model.VerificationToken;

public interface VerificationTokenService {

    VerificationToken createVerificationToken();
    void saveToken(VerificationToken verificationToken);
    VerificationToken findByToken (String token);
    void deleteVerificationTokenObject(VerificationToken verificationToken);
    void removeTokenByToken(String token);

}
