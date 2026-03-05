package com.auth_gradel.gradle.project.service;

import com.auth_gradel.gradle.project.entity.UserInfo;
import com.auth_gradel.gradle.project.entity.UserRoles;
import com.auth_gradel.gradle.project.model.UserInfoDto;
import com.auth_gradel.gradle.project.repository.RoleRepository;
import com.auth_gradel.gradle.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


@Component
@Data
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    @Autowired
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user=userRepository.findByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("could not found user ");
        }

        return new CustomUserDetail(user);

    }


    public UserInfo checkIfUserAlreadyExist(UserInfoDto userInfoDto)
    {
        return userRepository.findByUsername(userInfoDto.getUsername());
    }

    public Boolean signupUser(UserInfoDto userInfoDto)
    {
        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));

        if(Objects.nonNull(checkIfUserAlreadyExist(userInfoDto)))
        {
            return false;
        }

        String userId= UUID.randomUUID().toString();
        UserRoles role=roleRepository.findByRoleName("ROLE_USER").orElseThrow(
                ()-> new RuntimeException("role not found"));

        Set<UserRoles> roles =new HashSet<>();
        roles.add(role);

        userRepository.save(new UserInfo(userId,userInfoDto.getUsername(),
                userInfoDto.getPassword(),roles));

        return true;
    }

}
