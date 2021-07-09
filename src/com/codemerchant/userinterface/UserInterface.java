package com.codemerchant.userinterface;

import com.codemerchant.assistmakers.PlayerAssists;
import com.codemerchant.cleansheets.CleanSheets;
import com.codemerchant.club.Club;
import com.codemerchant.footballer.Footballer;
import com.codemerchant.goalstats.GoalStatistics;
import com.codemerchant.playerseasonstats.InDepthPlayerStats;
import com.codemerchant.toppassers.PassingStatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

public class UserInterface {
    private List<Club> leagueTable;
    private final Scanner scanner;
    private List<GoalStatistics> goalStatisticsTable;
    private List<PassingStatistics> passingStatistics;
    private List<CleanSheets> cleanSheets;
    private List<PlayerAssists> playerAssists;
    private List<InDepthPlayerStats> inDepthPlayerStats;

    public UserInterface(List<Club> leagueTable, List<GoalStatistics> goalStatisticsTable, List<PassingStatistics> passingStatisticsList,
                         List<CleanSheets> cleanSheetsList, List<PlayerAssists> playerAssistsList, List<InDepthPlayerStats> inDepthPlayerStats, Scanner scanner) {
        this.leagueTable = leagueTable;
        this.scanner = scanner;
        this.goalStatisticsTable = goalStatisticsTable;
        this.passingStatistics = passingStatisticsList;
        this.cleanSheets = cleanSheetsList;
        this.playerAssists = playerAssistsList;
    }

    public void start() {
        /*System.out.print("Enter file name: ");
        String fileName = this.scanner.nextLine();

        this.leagueTable = readClubs(fileName);
        generateLeagueTable(this.leagueTable);*/

        while (true) {

            System.out.print("Premier League 2020/21 Stats Centre" + "\nWhich stats do you want to view today?" + "\n1. View Current Premier League Table\n2. View Current Top 10 Goal Scorers" +
                    "\n3. View Club Statistics\n4. View Full Player Stats\n5. Goals Scored So Far" + "\n6. View Current Top 10 Assist Chart"
                    + "\n7. View Current Top 10 Most Passes Chart" + "\n8. View Current Top 10 Clean Sheet Chart\n9. View Player Stats By Club" +
                    "\n10. Exit" + "\nEnter the corresponding number: ");

            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("10")) {
                System.out.println("Thank you for visiting our stats centre");
                break;
            }

            readCommand(command);
        }

    }

    private void readCommand(String command) {

        if (command.equalsIgnoreCase("1")) {
            // System.out.print("Enter file name: ");
            // this.leagueTable = readClubs(scanner.nextLine()); //if we wanted to pass the file name ourselves at run time.We won't though. readClubs takes a String param

            this.leagueTable = readClubs();
            generateLeagueTable(this.leagueTable);
        } else if (command.equalsIgnoreCase("2")) {

            /*this.goalStatisticsTable = readGoalStats();
            generateScorersChart(this.goalStatisticsTable);*/

            viewTopTenGoalScorers(inDepthPlayerStats);

        } else if (command.equalsIgnoreCase("3")) {

            System.out.print("Enter club to search for: ");
            viewClubSeasonStats(leagueTable, scanner.nextLine());
        } else if (command.equalsIgnoreCase("4")) {
            inDepthPlayerStats = readPlayerStats();
            generatePlayerStats(inDepthPlayerStats);
        } else if (command.equalsIgnoreCase("5")) {
            viewTotalGoalsScoredSoFar(leagueTable);
        } else if (command.equalsIgnoreCase("6")) {
            //playerAssists = readAssistStats();
            //generateAssistChart(playerAssists);

            long start = System.currentTimeMillis();
            viewTopTenAssisters(inDepthPlayerStats);
            long end = System.currentTimeMillis();
            System.out.println("Elapsed Time in milli seconds: " + (end - start));


        } else if (command.equalsIgnoreCase("7")) {
            passingStatistics = readPassingStats();
            generatePassersChart(passingStatistics);
        } else if (command.equalsIgnoreCase("8")) {
            cleanSheets = readCleanSheetStats();
            generateCleanSheetChart(cleanSheets);
        } else if (command.equals("9")) {
            System.out.print("Enter club name: ");
            viewSquads(inDepthPlayerStats, scanner.nextLine());
        }
    }

    private static List<Club> readClubs() {
        List<Club> clubList = new ArrayList<>();

        try {
            Files.lines(Paths.get("premierleague.txt"))
                    .map(row -> row.split(";"))
                    .map(parts -> new Club(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), Integer.parseInt(parts[7]), Integer.parseInt(parts[8]), parts[9]))
                    .forEach(clubList::add);


        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

        return clubList;
    }

    private static void generateLeagueTable(List<Club> table) {

        getTableTopBar();

        /*//Sorting using the help of the Comparable interface's compareTo() method implemented in Club class
        Collections.sort(leagueTable);
        leagueTable.stream()
             .forEach(System.out::println);*/

        Comparator<Club> comparator = Comparator
                .comparing(Club::getPoints)//We first compare who has the most points between two clubs
                .thenComparing(Club::getGoalDifference) //we then check who has a better goal difference if points are matched by both clubs
                .thenComparing(Club::getGoalsFor) //we then check who has scored the most goals if everything above is matched by both clubs
                .thenComparing(Club::getGamesWon) //we then check who has won the most games if everything above is matched by both clubs
                .reversed()//We then call this method to help sort everything above from highest to lowest since comparator naturally sorts in ascending order.
                .thenComparing(Club::getGoalsAgainst)//we then check who has the lowest goals conceded if everything above is matched by both clubs
                .thenComparing(Club::getGamesLost)//we then check who has lost the least amount of matches if everything above is matched by both clubs
                .thenComparing(Club::getGamesDrawn)//we then check who has drawn the least amount of matches  if everything above is matched by both clubs
                .thenComparing(Club::getClubName); //Lastly, we'll sort by club name if everything above is matched by both clubs from highest to lowest since comparator naturally sorts in ascending order.


        Collections.sort(table, comparator);

       /* table.stream()
                .forEach(System.out::println);*/

        IntStream.range(0, table.size())
                .mapToObj(i -> (i + 1) + "" + table.get(i))
                .forEach(System.out::println);

        System.out.println("\nLeague table after Matchday 36");
    }

    private static List<InDepthPlayerStats> readPlayerStats() {
        List<InDepthPlayerStats> inDepthPlayerStatsList = new ArrayList<>();

        try {
            Files.lines(Paths.get("stats.txt"))
                    .map(rows -> rows.split(";"))
                    .map(parts -> new InDepthPlayerStats(parts[0].trim(), parts[1].trim(), parts[2].trim(),
                            parts[3].trim(), parts[4].trim(),
                            Integer.parseInt(parts[5].trim()), Integer.parseInt(parts[6].trim()),
                            Integer.parseInt(parts[7].trim()), Integer.parseInt(parts[8].trim()),
                            Integer.parseInt(parts[9].trim()), Integer.parseInt(parts[10].trim()),
                            Integer.parseInt(parts[11].trim()),
                            Integer.parseInt(parts[12].trim()), Double.parseDouble(parts[13].trim())))
                    .forEach(inDepthPlayerStatsList::add);
        } catch (IOException ioException) {

            System.out.println(ioException.getMessage());
        }

        return inDepthPlayerStatsList;
    }

    private static void generatePlayerStats(List<InDepthPlayerStats> inDepthPlayerStatsList) {

        getInDepthPlayersStatsBar();

        Comparator<InDepthPlayerStats> inDepthPlayerStatsComparator = Comparator
                .comparing(InDepthPlayerStats::getSurname)
                .thenComparing((InDepthPlayerStats::getName));

        Collections.sort(inDepthPlayerStatsList, inDepthPlayerStatsComparator);

        IntStream.range(0, inDepthPlayerStatsList.size())
                .mapToObj(i -> (i + 1) + "\t" + inDepthPlayerStatsList.get(i))
                .forEach(System.out::println);
    }

    private static List<GoalStatistics> readGoalStats() {
        List<GoalStatistics> goalStatistics = new ArrayList<>();
        try {
            Files.lines(Paths.get("topscorers.txt"))
                    .map(row -> row.split(";"))
                    .map(parts -> new GoalStatistics(parts[0].trim(), parts[1].trim(), parts[2].trim(),
                            parts[3].trim(), parts[4].trim(), Integer.parseInt(parts[5].trim())))
                    .forEach(goalStatistics::add);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return goalStatistics;
    }

    private static void generateScorersChart(List<GoalStatistics> goalStatistics) {
        //String name, String surname, String club, String position, int goalsScored
        System.out.println("\t" + String.format("%-20s %-20s %-20s %-22s %-6s %-10s", "Name",
                "Surname", "Club", "Country", "Pos", "Goals") +
                "\n=======================================================================================================");

        Comparator<GoalStatistics> comparator1 = Comparator
                .comparing(GoalStatistics::getGoalsScored)
                .reversed()
                .thenComparing(Footballer::getName)
                .thenComparing(Footballer::getSurname);

        Collections.sort(goalStatistics, comparator1);

       /* goalStatistics.stream()
                .forEach(System.out::println);*/

        IntStream.range(0, goalStatistics.size())
                .mapToObj(i -> (i + 1) + "\t" + goalStatistics.get(i))
                .forEach(System.out::println);

        System.out.println("Top goal scorers chart as of 03/05/2021");
        System.out.println();
        System.out.println();
    }

    private static List<CleanSheets> readCleanSheetStats() {
        List<CleanSheets> cleanSheetsList = new ArrayList<>();

        try {
            Files.lines(Paths.get("gkcleansheets.txt"))
                    .map(row -> row.split(";"))
                    .map(parts -> new CleanSheets(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim(), Integer.parseInt(parts[5].trim())))
                    .forEach(cleanSheetsList::add);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return cleanSheetsList;
    }

    private static void generateCleanSheetChart(List<CleanSheets> cleanSheetsList) {
        System.out.println("\tName" + "\t\tSurname" + "\t\t\t\tClub" + "\t\t\t\t\tCountry" + "\t\t\tPos" + "\t\tClean Sheets" +
                "\n==================================================================================================");

        Comparator<CleanSheets> cleanSheetsComparator = Comparator
                .comparing(CleanSheets::getCleansheets)
                .reversed()
                .thenComparing(Footballer::getName)
                .thenComparing(Footballer::getSurname)
                .thenComparing(Footballer::getClub);

        Collections.sort(cleanSheetsList, cleanSheetsComparator);

        /*cleanSheetsList.stream()
                .forEach(System.out::println);*/

        IntStream.range(0, cleanSheetsList.size())
                .mapToObj(i -> (i + 1) + "\t" + cleanSheetsList.get(i))
                .forEach(System.out::println);

        System.out.println("Clean sheet stats as of 03/05/2021");
        System.out.println();
        System.out.println();

    }

    private static List<PassingStatistics> readPassingStats() {
        List<PassingStatistics> passingStatisticsList = new ArrayList<>();

        try {
            Files.lines(Paths.get("passmasters.txt"))
                    .map(row -> row.split(";"))
                    .map(parts -> new PassingStatistics(parts[0].trim(), parts[1].trim(),
                            parts[2].trim(), parts[3].trim(), parts[4].trim(), Integer.parseInt(parts[5].trim())))
                    .forEach(passingStatisticsList::add);

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

        return passingStatisticsList;
    }

    private static void generatePassersChart(List<PassingStatistics> passingStatisticsList) {


        System.out.println("\t" + String.format("%-20s %-20s %-20s %-22s %-6s %-10s", "Name",
                "Surname", "Club", "Country", "Pos", "Passes") +
                "\n===========================================================================================================");

        Comparator<PassingStatistics> passingStatisticsComparator = Comparator
                .comparing(PassingStatistics::getCompletedPasses)
                .reversed()
                .thenComparing(Footballer::getName)
                .thenComparing(Footballer::getName)
                .thenComparing(Footballer::getSurname);

        Collections.sort(passingStatisticsList, passingStatisticsComparator);


        IntStream.range(0, passingStatisticsList.size())
                .mapToObj(i -> (i + 1) + "\t" + passingStatisticsList.get(i))
                .forEach(System.out::println);

        //code above is the same as
      /*  for(int i =0; i < passingStatisticsList.size(); i++){
            System.out.println((i+1) + " " + passingStatisticsList.get(i));
        }*/

        System.out.println("Most passes stats as of 03/05/2021");
        System.out.println();
        System.out.println();

    }

    private static List<PlayerAssists> readAssistStats() {

        List<PlayerAssists> playerAssistsList = new ArrayList<>();

        try {
            Files.lines(Paths.get("topcreators.txt"))
                    .map(row -> row.split(";"))
                    .map(parts -> new PlayerAssists(parts[0].trim(), parts[1].trim(),
                            parts[2].trim(), parts[3].trim(), parts[4].trim(), Integer.parseInt(parts[5].trim())))
                    .forEach(playerAssistsList::add);

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return playerAssistsList;
    }

    private static void generateAssistChart(List<PlayerAssists> playerAssistsList) {


        System.out.println("\t" + String.format("%-20s %-20s %-20s %-22s %-6s %-10s", "Name",
                "Surname", "Club", "Country", "Pos", "Assists") +
                "\n========================================================================================================");

        Comparator<PlayerAssists> playerAssistsComparator = Comparator
                .comparing(PlayerAssists::getAssists)
                .reversed()
                .thenComparing(Footballer::getName)
                .thenComparing(Footballer::getSurname)
                .thenComparing(Footballer::getClub);

        playerAssistsList.sort(playerAssistsComparator);

        /*playerAssistsList.stream()
                .forEach(System.out::println);*/

        IntStream.range(0, playerAssistsList.size()) //playerAssistsList.size()-5 to get the top 5
                .mapToObj(i -> (i + 1) + "\t" + playerAssistsList.get(i))
                .forEach(System.out::println);
    }

    private static void viewClubSeasonStats(List<Club> clubList, String clubName) {
        String club = clubName.toLowerCase();
        getTableTopBar();

        clubList = readClubs();
        clubList.stream()
                .filter(searchedClub -> searchedClub.getClubName().toLowerCase().contains(club))
                .forEach(System.out::println);


        System.out.println();
    }

    private static void viewTopTenAssisters(List<InDepthPlayerStats> inDepthPlayerStatsList) {
        getInDepthPlayersStatsBar();

        inDepthPlayerStatsList = readPlayerStats();

        Comparator<InDepthPlayerStats> inDepthPlayerStatsComparator = Comparator
                .comparing(InDepthPlayerStats::getAssists)
                .reversed();

        Collections.sort(inDepthPlayerStatsList, inDepthPlayerStatsComparator);

        int bound = inDepthPlayerStatsList.size() - 497;
        for (int i = 0; i < bound; i++) {
            String s = (i + 1) + "\t" + inDepthPlayerStatsList.get(i);
            System.out.println(s);
        }

       /* List<InDepthPlayerStats> finalInDepthPlayerStatsList = inDepthPlayerStatsList;
        IntStream.range(0,bound)
                .mapToObj(i -> (i+1)+ "\t" + finalInDepthPlayerStatsList.get(i))
                .forEach(System.out::println);*/

    }

    private static void viewTopTenGoalScorers(List<InDepthPlayerStats> inDepthPlayerStatsList) {
        getInDepthPlayersStatsBar();

        inDepthPlayerStatsList = readPlayerStats();

        Comparator<InDepthPlayerStats> inDepthPlayerStatsComparator = Comparator
                .comparing(InDepthPlayerStats::getGoals)
                .reversed()
                .thenComparing(InDepthPlayerStats::getName)
                .thenComparing(InDepthPlayerStats::getSurname);

        Collections.sort(inDepthPlayerStatsList, inDepthPlayerStatsComparator);

        int bound = inDepthPlayerStatsList.size() - 497;
        /*for (int i = 0; i < bound; i += 1) {
            String printPLayer = (i + 1) + "\t" + inDepthPlayerStatsList.get(i);
            System.out.println(printPLayer);*/


        List<InDepthPlayerStats> finalInDepth = inDepthPlayerStatsList;

        IntStream.range(0, finalInDepth.size() - 497)
                .mapToObj(i -> (i + 1) + "\t" + finalInDepth.get(i))
                .forEach(System.out::println);
    }


    private static void viewSquads(List<InDepthPlayerStats> inDepthPlayerStatsList, String club) {
        String clubName = club.toLowerCase();
        System.out.println(String.format("%-20s %-20s %-20s %-22s %-6s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s ", "Name",
                "Surname", "Club", "Country", "Pos", "Age", "Apps", "Minutes", "Goals", "Assists", "Yellows", "Reds", "MOTM", "Avg Rating")
                + "\n==================================================================================================" +
                "=====================================================================================================");


        inDepthPlayerStatsList = readPlayerStats();
        inDepthPlayerStatsList.stream()
                .filter(searchedClub -> searchedClub.getClub().toLowerCase().contains(clubName))
                .forEach(System.out::println);
        System.out.println("\nCorrect as of 11/05/21");


    }

    private static void viewTotalGoalsScoredSoFar(List<Club> totalGoals) {
        totalGoals = readClubs();

        int sum = totalGoals.stream()
                .map(Club::getGoalsFor)
                .reduce(0, Integer::sum);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Total goals scored so far in the 2020/21 Premier League season: " + sum + "\n==============================================================");


    }

    private static void getTableTopBar() {
        System.out.println("\tClub" + "\t\t\t\tGames Played" + "\tPts" + "\t\tWon" + "\t" + "\tDraws" + "\t\tLost" + "\tGF" + "\t\tGA" + "\t\tGD" + "\t\tLast 5 Matches" +
                "\n==============================================================================================================");
    }

    private static void getInDepthPlayersStatsBar() {
        System.out.println("\t" + String.format("%-20s %-20s %-20s %-22s %-6s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s ", "Name",
                "Surname", "Club", "Country", "Pos", "Age", "Apps", "Minutes", "Goals", "Assists", "Yellows", "Reds", "MOTM", "Avg Rating")
                + "\n==================================================================================================" +
                "=====================================================================================================");
    }
}
