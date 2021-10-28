package com.iamshashiraj.splitwise.service;

import com.iamshashiraj.splitwise.model.Expense;
import com.iamshashiraj.splitwise.model.ExpenseRequestEntity;
import com.iamshashiraj.splitwise.model.Group;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ExpenseService {
	List<Expense> getExpenseByGroup(Group group);
	List<Expense> findExpenseByGroupAndDateAfter(Group group, LocalDate date);
	Expense addExpense(Expense expense);
	Expense getExpenseFromRequestEntity(ExpenseRequestEntity requestEntity);
}
