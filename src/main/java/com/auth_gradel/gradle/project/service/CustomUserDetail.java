package com.auth_gradel.gradle.project.service;

import com.auth_gradel.gradle.project.entity.UserInfo;
import com.auth_gradel.gradle.project.entity.UserRoles;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetail extends UserInfo implements UserDetails {

    String password;
    String username;

    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetail(UserInfo byUserName) {
        this.username = byUserName.getUsername();
        this.password = byUserName.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();

        for (UserRoles role : byUserName.getRoles()) {
            auths.add(new SimpleGrantedAuthority(role.getRoleName().toUpperCase()));
        }

        this.authorities = auths;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
