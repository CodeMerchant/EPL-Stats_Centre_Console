package com.codemerchant;

import com.codemerchant.assistmakers.PlayerAssists;
import com.codemerchant.cleansheets.CleanSheets;
import com.codemerchant.club.Club;
import com.codemerchant.goalstats.GoalStatistics;
import com.codemerchant.playerseasonstats.InDepthPlayerStats;
import com.codemerchant.toppassers.PassingStatistics;
import com.codemerchant.userinterface.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //String clubName, int gamesPlayed, int pointsWon, int gamesWon, int gamesDrawn, int gamesLost, int goalsFor, int goalsAgainst, int goalDifference, String form
        List<Club> leagueTable = new ArrayList<>();

        List<GoalStatistics> goalStatisticsList = new ArrayList<>();

        List<PassingStatistics> passingStatisticsList = new ArrayList<>();

        List<CleanSheets> cleanSheetsList = new ArrayList<>();

        List<PlayerAssists> playerAssistsList = new ArrayList<>();
        List<InDepthPlayerStats> inDepthPlayerStats = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        UserInterface userInterface = new UserInterface(leagueTable, goalStatisticsList, passingStatisticsList,
                cleanSheetsList, playerAssistsList, inDepthPlayerStats,scanner);

        userInterface.start();


    }
}
