package com.auth_gradel.gradle.project.controller;

import com.auth_gradel.gradle.project.entity.RefreshToken;
import com.auth_gradel.gradle.project.model.UserInfoDto;
import com.auth_gradel.gradle.project.response.JwtResponseDTO;
import com.auth_gradel.gradle.project.service.JwtService;
import com.auth_gradel.gradle.project.service.RefreshTokenService;
import com.auth_gradel.gradle.project.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("auth/v1/signup")
    public ResponseEntity SignUp(@RequestBody UserInfoDto userInfoDto)
    {
        try {
            Boolean isSignuped=userDetailsService.signupUser(userInfoDto);
            if(Boolean.FALSE.equals(isSignuped)){
                return new ResponseEntity("Already Exists ", HttpStatus.BAD_REQUEST);

            }

            RefreshToken refreshToken=refreshTokenService.createRefreshToken(userInfoDto.getUsername());

            String jwtToken=jwtService.GenerateToken(userInfoDto.getUsername());

            return new ResponseEntity(JwtResponseDTO.builder().
                    accessToken(jwtToken).token(refreshToken.getToken()).
                    build(),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity("Exception in user service",HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
