package com.iamshashiraj.splitwise.service;

import com.iamshashiraj.splitwise.model.Expense;
import com.iamshashiraj.splitwise.model.ExpenseType;
import com.iamshashiraj.splitwise.model.Group;
import com.iamshashiraj.splitwise.model.User;
import com.iamshashiraj.splitwise.repos.UserCrudRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    private static final String DELIMITER = " ";

    @Autowired
    private UserCrudRepo crudRepo;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ExpenseService expenseService;

    @Override
    public User addUser(User user) {
        return crudRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return crudRepo.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return crudRepo.getById(id);
    }

    @Override
    public List<String> getUserExpenseInGroupByDate(Integer groupId, Integer userId, String date) {
        Group group = groupService.getGroupById(groupId);
        List<Expense> expenses = expenseService.findExpenseByGroupAndDateAfter(group, LocalDate.parse(date, formatter));
        List<String> expensesString = new ArrayList<>();
        System.out.println("hi");
        System.out.println(expenses);
        for(Expense expense: expenses) {
            if (expense.getPaidBy().equals(userId)) {
                expensesString.add("User " + expense.getPaidBy() + " paid Rupees " + expense.getAmount() + " on " + expense.getDate() + " with users " + expense.getAssociatedUsers() + " for " + expense.getName());
            } else {
                String[] users = expense.getAssociatedUsers().split(DELIMITER);
                String[] shares = expense.getIndividualShares().split(DELIMITER);
                for (int i =0; i < users.length; i++) {
                    String currentUser = users[i];
                    if (Integer.parseInt(currentUser) == userId) {
                        if (expense.getExpenseType().equals(ExpenseType.EQUAL_SPLIT)) {
                            expensesString.add("User " + userId + " spent Rupees " + expense.getAmount() / users.length + " on " + expense.getDate() + " with users " + expense.getAssociatedUsers() + " for " + expense.getName());
                        } else {
                            expensesString.add("User " + userId + " spent Rupees " + shares[i] + " on " + expense.getDate() + " with users " + expense.getAssociatedUsers() + " for " + expense.getName());
                        }
                    }
                }
            }

        }
        return expensesString;
    }
}
