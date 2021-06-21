package com.codemerchant.footballer;

public class Footballer {
    private String name;
    private String surname;
    private String club;
    private String nationality;
    private String position;

    /*public Footballer(String name, String surname, String club, String position) {
        this.name = name;
        this.surname = surname;
        this.club = club;
        this.position = position;
    }*/

    public Footballer(String name, String surname, String club, String nationality, String position) {
        this.name = name;
        this.surname = surname;
        this.club = club;
        this.nationality = nationality;
        this.position = position;
    }

    public String getNationality() {
        return nationality;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getClub() {
        return club;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-22s %-7s",name,surname,club,nationality,position);
    }
}
