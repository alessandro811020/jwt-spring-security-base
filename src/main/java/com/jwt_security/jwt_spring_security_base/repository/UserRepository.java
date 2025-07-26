package com.jwt_security.jwt_spring_security_base.repository;

import com.jwt_security.jwt_spring_security_base.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
