package com.iamshashiraj.splitwise.repos;

import com.iamshashiraj.splitwise.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupCrudRepo extends JpaRepository<Group, Integer> {
    List<Group> findAll();
    Group removeById(Integer id);
}