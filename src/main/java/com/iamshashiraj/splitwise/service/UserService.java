package com.iamshashiraj.splitwise.service;

import com.iamshashiraj.splitwise.model.Group;
import com.iamshashiraj.splitwise.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

	User addUser(User user);

	List<User> getAllUsers();

	User getUserById(Integer id);

	List<String> getUserExpenseInGroupByDate(Integer groupId, Integer userId, String date);
}
