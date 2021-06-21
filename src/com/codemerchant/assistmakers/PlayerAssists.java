package com.codemerchant.assistmakers;

import com.codemerchant.footballer.Footballer;

public class PlayerAssists extends Footballer {
    private int assists;

    public PlayerAssists(String name, String surname, String club, String nationality,String position, int assists) {
        super(name, surname, club,nationality, position);
        this.assists = assists;
    }

    public int getAssists() {
        return assists;
    }

    @Override
    public String toString() {
        return super.toString() + assists +
                "\n========================================================================================================";
    }
}
