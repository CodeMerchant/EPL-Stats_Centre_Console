package com.codemerchant.cleansheets;

import com.codemerchant.footballer.Footballer;

public class CleanSheets extends Footballer {
    private int cleansheets;

    public CleanSheets(String name, String surname, String club,String nationality, String position, int cleansheets) {
        super(name, surname, club,nationality, position);
        this.cleansheets = cleansheets;
    }

    public int getCleansheets() {
        return cleansheets;
    }

    @Override
    public String toString() {
        return super.toString() + "\t" + cleansheets +
                "\n==================================================================================================";
    }
}
