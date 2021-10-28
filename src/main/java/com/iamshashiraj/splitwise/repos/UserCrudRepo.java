package com.iamshashiraj.splitwise.repos;

import com.iamshashiraj.splitwise.model.Group;
import com.iamshashiraj.splitwise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCrudRepo extends JpaRepository<User, Integer> {
    List<User> findAll();
}