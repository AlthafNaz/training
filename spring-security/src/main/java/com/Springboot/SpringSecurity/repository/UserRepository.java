package com.Springboot.SpringSecurity.repository;

import com.Springboot.SpringSecurity.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository <Users, Integer> {

    Optional<Users> findByEmail(String email);
}
