package com.springmicro.foodometer.repository;

import com.springmicro.foodometer.constants.UserRole;
import com.springmicro.foodometer.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByUserRole(UserRole userRole);
    List<User> findAllByUserRoleIn(List<UserRole> userRoles);
}
