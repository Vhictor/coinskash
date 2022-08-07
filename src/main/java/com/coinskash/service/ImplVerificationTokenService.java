package com.coinskash.service;

import com.coinskash.model.AppUser;
import com.coinskash.model.VerificationToken;
import com.coinskash.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

@Service @RequiredArgsConstructor
public class ImplVerificationTokenService implements VerificationTokenService{

    @Autowired
    private final VerificationTokenRepository verificationTokenRepository;

    @Value("${site.base.url}")
    private String baseURL;

    @Override
    public VerificationToken createVerificationToken() {
       String token = generateRandomBase64Token(16);
       VerificationToken verificationToken = new VerificationToken();
       verificationToken.setToken(token);
       verificationToken.setExpireDate(LocalDateTime.now().plusHours(8));
       this.saveToken(verificationToken);
       return verificationToken;

    }

    @Override
    public void saveToken(VerificationToken verificationToken) {
    verificationTokenRepository.save(verificationToken);
    }

    @Override
    public  VerificationToken findByToken(String token) {
       return  verificationTokenRepository.findByToken(token);
    }

    @Override
    public void deleteVerificationTokenObject(VerificationToken verificationToken) {
    verificationTokenRepository.delete(verificationToken);
    }

    @Override
    public void removeTokenByToken(String token) {
        verificationTokenRepository.removeByToken(token);
    }

    @Override
    public String buildVerificationUrl(VerificationToken verificationToken) {
        String url = UriComponentsBuilder.fromHttpUrl(baseURL).path("/api/register/verify")
                .queryParam("token",verificationToken.getToken()).toUriString();
        return url;
    }


    // Generate random string
    public static String generateRandomBase64Token(int byteLength) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[byteLength];
        secureRandom.nextBytes(token);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(token); //base64 encoding
    }
}
