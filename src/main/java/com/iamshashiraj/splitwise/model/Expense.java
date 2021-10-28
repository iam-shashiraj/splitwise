package com.iamshashiraj.splitwise.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private ExpenseType expenseType;
    private Integer paidBy;
    private LocalDate date;
    @OneToOne(cascade = CascadeType.ALL)
    private Group group;
    private Double amount;
    // FIXME: make it List<Users>
    String associatedUsers;
    // FIXME: make it List<Double>
    String individualShares;

    public Expense() {
    }

    public Expense(String name, ExpenseType expenseType, Integer paidBy, LocalDate date, Group group, Double amount, String associatedUsers, String individualShares) {
        this.name = name;
        this.expenseType = expenseType;
        this.paidBy = paidBy;
        this.date = date;
        this.group = group;
        this.amount = amount;
        this.associatedUsers = associatedUsers;
        this.individualShares = individualShares;
    }

    public String getIndividualShares() {
        return individualShares;
    }

    public void setIndividualShares(String individualShares) {
        this.individualShares = individualShares;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public Integer getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Integer paidBy) {
        this.paidBy = paidBy;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAssociatedUsers() {
        return associatedUsers;
    }

    public void setAssociatedUsers(String associatedUsers) {
        this.associatedUsers = associatedUsers;
    }

    @Override
    public String toString() {
        return "Expense{" +
                ", name='" + name + '\'' +
                ", expenseType=" + expenseType +
                ", paidBy=" + paidBy +
                ", date=" + date +
                ", group=" + group +
                ", amount=" + amount +
                ", associatedUsers='" + associatedUsers + '\'' +
                ", individualShares='" + individualShares + '\'' +
                '}';
    }
}
