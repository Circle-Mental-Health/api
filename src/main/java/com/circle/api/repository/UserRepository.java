package com.circle.api.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

import com.circle.api.model.User;

@EnableScan
public interface UserRepository {

  Optional<User> findByUserId(String id);

  User createUser(User user);

  User updateUser(User user);
}
