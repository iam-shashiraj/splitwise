package com.iamshashiraj.splitwise.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ExpenseRequestEntity {
    private String name;
    private String expenseType;
    private Integer paidBy;
    private Double amount;
    private Integer groupId;
    private String date;
    private Set<Integer> associatedUsers;
    private List<Integer> individualShares;

    public ExpenseRequestEntity() {
    }

    public List<Integer> getIndividualShares() {
        return individualShares;
    }

    public void setIndividualShares(List<Integer> individualShares) {
        this.individualShares = individualShares;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public Integer getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Integer paidBy) {
        this.paidBy = paidBy;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Set<Integer> getAssociatedUsers() {
        return associatedUsers;
    }

    public void setAssociatedUsers(Set<Integer> associatedUsers) {
        this.associatedUsers = associatedUsers;
    }
}
