package com.auth_gradel.gradle.project.repository;

import com.auth_gradel.gradle.project.entity.UserRoles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<UserRoles,Long> {
    Optional<UserRoles> findByRoleName(String roleName);
}
