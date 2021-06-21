package com.codemerchant.playerseasonstats;

import com.codemerchant.footballer.Footballer;

public class PlayerSeasonStats extends Footballer {
    private int matchesPlayed;
    private int minutesPlayed;
    private int goalsScored;
    private int completedPasses;
    private int assists;


    public PlayerSeasonStats(String name, String surname, String club,String nationality, String position, int matchesPlayed,
                             int minutesPlayed, int goalsScored, int completedPasses, int assists) {
        super(name, surname, club,nationality, position);
        this.matchesPlayed = matchesPlayed;
        this.minutesPlayed = minutesPlayed;
        this.goalsScored = goalsScored;
        this.completedPasses = completedPasses;
        this.assists = assists;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getCompletedPasses() {
        return completedPasses;
    }

    public int getAssists() {
        return assists;
    }
}
