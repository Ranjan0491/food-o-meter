package com.springmicro.foodometer.repository;

import com.springmicro.foodometer.constants.UserRole;
import com.springmicro.foodometer.constants.UserStatus;
import com.springmicro.foodometer.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByUserRoleAndStatus(UserRole userRole, UserStatus userStatus);
    List<User> findAllByUserRoleIn(List<UserRole> userRoles);
    User findByEmailAndPhone(String email, String phone);
    User findByEmailOrPhone(String email, String phone);
}
