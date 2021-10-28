package com.iamshashiraj.splitwise.controller;

import com.iamshashiraj.splitwise.model.Expense;
import com.iamshashiraj.splitwise.model.Group;
import com.iamshashiraj.splitwise.model.User;
import com.iamshashiraj.splitwise.service.ExpenseService;
import com.iamshashiraj.splitwise.service.GroupService;
import com.iamshashiraj.splitwise.service.UserService;
import com.iamshashiraj.splitwise.utils.ExpenseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/splitwise/groups")
public class GroupsController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Group> addGroup(@RequestBody Group group){
        Group groupSaved = groupService.addGroup(group);
        return new ResponseEntity<Group>(groupSaved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Group>> getGroups(){
        List<Group> groups = groupService.getAllGroups();
        return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
    }

    @GetMapping("/{groupId}/expenses")
    public ResponseEntity<List<String>> getAllExpensesByGroup(@PathVariable Integer groupId){
        Group group = groupService.getGroupById(groupId);
        List<Expense> expenses = expenseService.getExpenseByGroup(group);
        List<String> balanceSheet = ExpenseUtil.getBalanceSheet(expenses);
        return new ResponseEntity<List<String>>(balanceSheet, HttpStatus.OK);
    }

    /*@PostMapping("/{groupId}/users")
    public ResponseEntity<String> addUsersToGroup(@PathVariable Integer groupId, @RequestBody List<Integer> userIds){
        // TODO: move these logics to service layer
        Group group = groupService.getGroupById(groupId);
        List<User> groupUsers = new ArrayList<>();
        for(Integer id: userIds) {
            groupUsers.add(userService.getUserById(id));
        }
        group.setUsers(groupUsers);
        groupService.addGroup(group);
        return new ResponseEntity<String>("Added Users to group", HttpStatus.OK);
    }*/
}
