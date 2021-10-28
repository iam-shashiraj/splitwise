package com.iamshashiraj.splitwise.controller;

import com.iamshashiraj.splitwise.model.*;
import com.iamshashiraj.splitwise.service.ExpenseService;
import com.iamshashiraj.splitwise.service.GroupService;
import com.iamshashiraj.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/splitwise/users")
public class UsersController {

    @Autowired
    private UserService userService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User userSaved = userService.addUser(user);
        return new ResponseEntity<User>(userSaved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}/groups/{groupId}/expenses")
    public ResponseEntity<List<String>> GetUsersGroupExpensesByDate(@PathVariable Integer userId, @PathVariable Integer groupId, @RequestParam("date") String date){
        List<String> userExpenses = userService.getUserExpenseInGroupByDate(groupId, userId, date);
        return new ResponseEntity<List<String>>(userExpenses, HttpStatus.OK);
    }
}
