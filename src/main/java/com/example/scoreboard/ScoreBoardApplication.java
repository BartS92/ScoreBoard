package com.example.scoreboard;

import com.example.scoreboard.service.game.GamesManager;
import com.example.scoreboard.service.printer.Printer;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScoreBoardApplication implements CommandLineRunner {

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private GamesManager gamesManager;

    @Autowired
    private Printer printer;

    public static void main(String[] args) {
        SpringApplication.run(ScoreBoardApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started");
        var command = 0;
        while (command != 5) {
            printer.printMenu();

            try {
                command = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("undefined command, try again..");
                continue;
            }

            if (command > 5) {
                System.out.println("undefined command, try again..");
                continue;
            }

            switch (command) {
                case 1: {
                    readTeamsAndStartGameCommand();
                    break;
                }
                case 2: {
                    finishGameCommand();
                    break;
                }
                case 3: {
                    updateScoreCommand();
                    break;
                }
                case 4: {
                    printSummaryOfGamesByTotalScore();
                }
            }
            System.out.println("\n\n");
        }
        System.out.println("Closing application..");
        scanner.close();
    }

    private void readTeamsAndStartGameCommand() {
        String home = "";
        String away = "";
        while (away.isEmpty() || home.isEmpty() || away.equalsIgnoreCase(home)) {
            System.out.println("Write a name of the a home team");
            home = readTeam();

            System.out.println("Write a name of the a away team");
            away = readTeam();
            if (away.equalsIgnoreCase(home)) {
                System.out.println("Teams names should be different, try again");
            }
        }
    }

    private String readTeam() {
        var team = scanner.next();
        while (gamesManager.teamExists(team)) {
            team = scanner.next();
        }

        return team;
    }

    private void finishGameCommand() {
        System.out.println("Select a game to complete: ");
        var games = gamesManager.getGames();
        printer.printGames(games,true);

        while (true) {
            var gameNumber = scanner.nextInt();
            if (gameNumber > games.size() + 1) {
                System.out.println("undefined command!");
            } else {
                gamesManager.finishGame(gameNumber - 1);
            }

            break;
        }
    }

    private void updateScoreCommand() {
        System.out.println("Select score upgrade game: ");
        var games = gamesManager.getGames();
        printer.printGames(games, true);

        while (true) {
            var gameNumber = scanner.nextInt();
            if (gameNumber > games.size() + 1) {
                System.out.println("undefined command!");
            } else {
                if (gameNumber < games.size() + 1) {
                    gamesManager.updateScore(gameNumber);
                }
                break;
            }
        }
    }

    private void printSummaryOfGamesByTotalScore() {
        printer.printGames(gamesManager.getSummaryOfGamesByTotalScore(), true);
    }


}
