package com.iamshashiraj.splitwise.repos;

import com.iamshashiraj.splitwise.model.Expense;
import com.iamshashiraj.splitwise.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ExpenseCrudRepo extends JpaRepository<Expense, Integer> {
    List<Expense> findExpenseByGroup(Group group);
    List<Expense> findExpenseByGroupAndDateAfter(Group group, LocalDate date);
}