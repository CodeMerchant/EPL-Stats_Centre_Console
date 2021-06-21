package com.codemerchant.playerseasonstats;

import com.codemerchant.footballer.Footballer;

public class InDepthPlayerStats extends Footballer {
    private int age;
    private int appearances;
    private int minutesPlayed;
    private int goals;
    private int assists;
    private int yellowCards;
    private int redCards;
    private int manOfTheMatch;
    private double averageRating;
    //Name ,          Surname,         Club,              Country;           Position,Age,Apps,Minutes,Goals,Assists,Yellow,Red,MOTM,Average Rating
    public InDepthPlayerStats(String name, String surname, String club, String nationality, String position,int age,
                              int appearances,int minutesPlayed,int goals,int assists,int yellowCards,
                              int redCards,int manOfTheMatch,double averageRating) {
        super(name, surname, club, nationality, position);
        this.age = age;
        this.appearances = appearances;
        this.minutesPlayed = minutesPlayed;
        this.goals = goals;
        this.assists = assists;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.manOfTheMatch = manOfTheMatch;
        this.averageRating = averageRating;

    }

    public int getAge() {
        return age;
    }

    public int getAppearances() {
        return appearances;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public int getManOfTheMatch() {
        return manOfTheMatch;
    }

    public double getAverageRating() {
        return averageRating;
    }

    @Override
    public String toString() {

        return super.toString() + String.format("%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %.1f ",age,appearances,
                minutesPlayed,goals,assists,yellowCards,redCards,manOfTheMatch,averageRating) + "\n==================================================================================================" +
                "=====================================================================================================";



    }
}
