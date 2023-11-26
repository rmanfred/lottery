package org.lottery.utils;

import java.util.HashSet;
import java.util.Set;

public class UserInfo {
    private String name;
    private double initialAmount = 0;
    private double currentAmount = 0;
    private int numOfSuperBets;
    private Set<Integer> mostPopularNums = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public int getNumOfSuperBets() {
        return numOfSuperBets;
    }

    public void setNumOfSuperBets(int numOfSuperBets) {
        this.numOfSuperBets = numOfSuperBets;
    }

    public Set<Integer> getMostPopularNums() {
        return mostPopularNums;
    }

    public void setMostPopularNums(Set<Integer> mostPopularNums) {
        this.mostPopularNums = mostPopularNums;
    }
}
