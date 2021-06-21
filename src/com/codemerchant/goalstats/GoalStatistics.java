package com.codemerchant.goalstats;

import com.codemerchant.footballer.Footballer;

public class GoalStatistics extends Footballer implements Comparable<GoalStatistics>{
    private int goalsScored;

    public GoalStatistics(String name, String surname, String club,String nationality, String position, int goalsScored) {
        super(name, surname, club, nationality, position);
        this.goalsScored = goalsScored;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    @Override
    public String toString() {
        return super.toString() +  + goalsScored +
                "\n=======================================================================================================";
    }

    @Override
    public int compareTo(GoalStatistics object) {
        return object.getGoalsScored() - this.goalsScored;
    }
}
