package com.auth_gradel.gradle.project.service;

import com.auth_gradel.gradle.project.entity.RefreshToken;
import com.auth_gradel.gradle.project.entity.UserInfo;

import com.auth_gradel.gradle.project.repository.RefreshTokenRepository;
import com.auth_gradel.gradle.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    public RefreshToken createRefreshToken(String username)
    {
        UserInfo userInfoExtracted  =userRepository.findByUsername(username);
        RefreshToken refreshToken=RefreshToken.builder()
                .userInfo(userInfoExtracted)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(1000*60*60*24*5))
                .build();

        return refreshTokenRepository.save(refreshToken);

    }

    public RefreshToken verifyExpiration(RefreshToken token)
    {
        if(token.getExpiryDate().compareTo(Instant.now())<0)
        {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken()+" expired refresh token");
        }
        return token;
    }

    public Optional<RefreshToken> findByToken(String token )
    {
        return refreshTokenRepository.findByToken(token);
    }




}
