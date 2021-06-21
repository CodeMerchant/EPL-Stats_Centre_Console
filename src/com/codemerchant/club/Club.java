package com.codemerchant.club;

public class Club implements Comparable<Club> {
    private String clubName;
    private int gamesPlayed;
    private int points;
    private int gamesWon;
    private int gamesDrawn;
    private int gamesLost;
    private int goalsFor;
    private int goalsAgainst;
    private int goalDifference;
    private String form;


    public Club(String clubName, int gamesPlayed, int pointsWon, int gamesWon, int gamesDrawn,
                int gamesLost, int goalsFor, int goalsAgainst, int goalDifference, String currentForm) {
        this.clubName = clubName;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.points = pointsWon;
        this.gamesDrawn = gamesDrawn;
        this.gamesLost = gamesLost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
        this.form = currentForm;

    }

    public int getPoints() {
        return points;
    }

    public String getClubName() {
        return clubName;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesDrawn() {
        return gamesDrawn;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public String getForm() {
        return form;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Club club = (Club) o;

        return clubName.equals(club.clubName);
    }

    @Override
    public int hashCode() {
        return clubName.hashCode();
    }


    @Override
    public String toString() {
        return "\t" + clubName + "\t" + gamesPlayed + "\t\t\t" + points + "\t\t" + gamesWon + "\t\t" + gamesDrawn + "\t\t\t" + gamesLost +
                "\t\t" + goalsFor + "\t\t" + goalsAgainst + "\t\t" + goalDifference + "   \t\t" + form +
                "\n==============================================================================================================";
//        return String.format("%-10s %20d %d %d %d %d %d %d %d %10s",clubName,gamesPlayed,points,gamesWon,gamesDrawn,gamesLost,goalsFor,goalsAgainst,goalDifference,form);
    }


    @Override
    public int compareTo(Club clubObject) {
        return clubObject.getPoints() - this.points;

    }
}
