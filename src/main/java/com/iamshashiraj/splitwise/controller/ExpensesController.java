package com.iamshashiraj.splitwise.controller;

import com.iamshashiraj.splitwise.model.Expense;
import com.iamshashiraj.splitwise.model.ExpenseRequestEntity;
import com.iamshashiraj.splitwise.model.ExpenseType;
import com.iamshashiraj.splitwise.service.ExpenseService;
import com.iamshashiraj.splitwise.service.GroupService;
import com.iamshashiraj.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/splitwise/expenses")
public class ExpensesController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody ExpenseRequestEntity requestEntity){
        Expense expense = expenseService.getExpenseFromRequestEntity(requestEntity);
        expenseService.addExpense(expense);
        return new ResponseEntity<Expense>(expense, HttpStatus.CREATED);
    }
}
