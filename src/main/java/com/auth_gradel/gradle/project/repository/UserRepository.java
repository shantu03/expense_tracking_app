package com.auth_gradel.gradle.project.repository;

import com.auth_gradel.gradle.project.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserInfo,Long> {

    public UserInfo findByUsername(String username);

}
