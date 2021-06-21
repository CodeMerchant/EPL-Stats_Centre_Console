package com.codemerchant.toppassers;


import com.codemerchant.footballer.Footballer;

public class PassingStatistics extends Footballer {
    private int completedPasses;



    public PassingStatistics(String name, String surname, String club,String nationality, String position, int completedPasses) {
        super(name, surname, club,nationality, position);
        this.completedPasses = completedPasses;
    }

    public int getCompletedPasses() {
        return completedPasses;
    }

    @Override
    public String toString() {
        return super.toString() + completedPasses +
                "\n===========================================================================================================";
    }
}
