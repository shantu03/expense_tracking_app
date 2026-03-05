package com.auth_gradel.gradle.project.model;

import com.auth_gradel.gradle.project.entity.UserInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserInfoDto extends UserInfo {
//user_name last_name --> ye MAP KARNE KELIYE



    private String userName;
    private String lastName;
    private String email;

    private Long phoneNumber;



}
