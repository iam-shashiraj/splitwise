package com.iamshashiraj.splitwise.utils;

import com.iamshashiraj.splitwise.model.Expense;
import com.iamshashiraj.splitwise.model.ExpenseType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseUtil {
    public static List<String> getBalanceSheet(List<Expense> expenses) {
        final String DELIMITER = " ";
        Map<Integer, Map<Integer, Double>> balanceSheet = new HashMap<>();

        for (Expense expense: expenses) {
            if (expense.getExpenseType() == ExpenseType.EQUAL_SPLIT) {
                String[] users = expense.getAssociatedUsers().split(DELIMITER);
                for(String user: users) {
                    Integer paidTo = Integer.parseInt(user);
                    Map<Integer, Double> balances = balanceSheet.get(expense.getPaidBy());
                    if (balances == null) {
                        balances = new HashMap<Integer, Double>();
                    }
                    Double splitAmount = expense.getAmount()/users.length;
                    if (!expense.getPaidBy().equals(paidTo)) {
                        if (balances.get(paidTo) == null) {
                            balances.put(paidTo, 0.0);
                        }
                        balances.put(paidTo, balances.get(paidTo) - splitAmount );
                    }
                    balanceSheet.put(expense.getPaidBy(), balances);
                }

            } else {
                // handles the case where ExpenseType is EXACT_SPLIT
                String[] users = expense.getAssociatedUsers().split(DELIMITER);
                String[] shares = expense.getIndividualShares().split(DELIMITER);
                for(int i=0; i< users.length; i++) {
                    Integer paidTo = Integer.parseInt(users[i]);
                    Map<Integer, Double> balances = balanceSheet.get(expense.getPaidBy());
                    if (balances == null) {
                        balances = new HashMap<Integer, Double>();
                    }
                    Double splitAmount = Double.parseDouble(shares[i]);
                    if (!expense.getPaidBy().equals(paidTo)) {
                        if (balances.get(paidTo) == null) {
                            balances.put(paidTo, 0.0);
                        }
                        balances.put(paidTo, balances.get(paidTo) - splitAmount );
                    }
                    balanceSheet.put(expense.getPaidBy(), balances);
                }
            }
        }
        System.out.println(balanceSheet);

        return getExpenseDetailsFromBalanceSheet(balanceSheet);
    }

    public static List<String> getExpenseDetailsFromBalanceSheet(Map<Integer, Map<Integer, Double>> balanceSheet) {
        List<String> details = new ArrayList<>();
        for (Integer userId: balanceSheet.keySet()) {
            details.addAll(getUserExpenseDetailsFromBalanceSheet(balanceSheet, userId));
        }
        return details;
    }

    public static List<String> getUserExpenseDetailsFromBalanceSheet(Map<Integer, Map<Integer, Double>> balanceSheet, Integer userId) {
        List<String> details = new ArrayList<>();
        for (Integer currentuser: balanceSheet.get(userId).keySet()) {
            Double currentDifference = 0.0;
            if (balanceSheet.get(currentuser) != null) {
                if (balanceSheet.get(currentuser).get(userId) != null) {
                    currentDifference = balanceSheet.get(currentuser).get(userId);
                }
            }
            Double actualAmount = balanceSheet.get(userId).get(currentuser) - currentDifference;
            if (actualAmount > 0) {
                details.add("User " + userId + " owes Rupees " + actualAmount + " to user " + currentuser + ".");
            } else if (actualAmount < 0) {
                details.add("User " + userId + " gets back Rupees " + Math.abs(actualAmount) + " from user " + currentuser + ".");
            } else {
                details.add("User " + userId + " and " + currentuser + "  are settled up.");
            }
        }
        return details;
    }

    // TODO: get rid off below code (used for testing purpose only)
    /*public static void main(String[] args) {
        List<Expense> expenses = new ArrayList<>();
        Expense e1 = new Expense("test", ExpenseType.EQUAL_SPLIT, 18, null, null, 2400D, "18 19 20 21 ", " ");
        Expense e2 = new Expense("test", ExpenseType.EXACT_SPLIT, 19, null, null, 1000D, "18 19 20 ", "600 200 200 ");
        Expense e3 = new Expense("test", ExpenseType.EXACT_SPLIT, 18, null, null, 100D, "18 19 20 ", "60 20 20 ");
        expenses.add(e1);
        expenses.add(e2);
        expenses.add(e3);
        ExpenseUtil util = new ExpenseUtil();
        util.getBalanceSheet(expenses);
    }*/
}
