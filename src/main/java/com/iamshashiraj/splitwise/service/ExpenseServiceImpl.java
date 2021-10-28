package com.iamshashiraj.splitwise.service;

import com.iamshashiraj.splitwise.model.Expense;
import com.iamshashiraj.splitwise.model.ExpenseRequestEntity;
import com.iamshashiraj.splitwise.model.ExpenseType;
import com.iamshashiraj.splitwise.model.Group;
import com.iamshashiraj.splitwise.repos.ExpenseCrudRepo;
import com.iamshashiraj.splitwise.repos.GroupCrudRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseCrudRepo crudRepo;

    @Autowired
    private GroupService groupService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    @Override
    public List<Expense> getExpenseByGroup(Group group) {

        return crudRepo.findExpenseByGroup(group);
    }

    @Override
    public Expense addExpense(Expense expense) {
        return crudRepo.save(expense);
    }

    @Override
    public List<Expense> findExpenseByGroupAndDateAfter(Group group, LocalDate date) {
        return crudRepo.findExpenseByGroupAndDateAfter(group, date);
    }

    @Override
    public Expense getExpenseFromRequestEntity(ExpenseRequestEntity requestEntity) {
        Expense expense = new Expense();
        expense.setName(requestEntity.getName());
        expense.setGroup(groupService.getGroupById(requestEntity.getGroupId()));
        expense.setPaidBy(requestEntity.getPaidBy());
        expense.setAmount(requestEntity.getAmount());
        expense.setDate(LocalDate.parse(requestEntity.getDate(), formatter));
        if (requestEntity.getExpenseType().equalsIgnoreCase("exact")) {
            expense.setExpenseType(ExpenseType.EXACT_SPLIT);
        }
        if (requestEntity.getExpenseType().equalsIgnoreCase("equal")) {
            expense.setExpenseType(ExpenseType.EQUAL_SPLIT);
        }
        // TODO: if expense type is not "exact" or "equals" throw appropriate http error
        StringBuilder users = new StringBuilder();
        for(Integer userId: requestEntity.getAssociatedUsers()) {
            users.append(userId + " ");
        }
        expense.setAssociatedUsers(users.toString());
        if (requestEntity.getExpenseType().equalsIgnoreCase("exact")) {
            StringBuilder shares = new StringBuilder();
            for (Integer share : requestEntity.getIndividualShares()) {
                shares.append(share + " ");
            }
            expense.setIndividualShares(shares.toString());
        } else {
            expense.setIndividualShares(" ");
        }
        return expense;
    }
}
