package com.example.scoreboard.runner;

import com.example.scoreboard.service.game.GamesManager;
import com.example.scoreboard.printer.Printer;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class ApplicationRunner implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private GamesManager gamesManager;

    @Autowired
    private Printer printer;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started");
        var command = 0;
        while (command != 5) {
            printer.printMenu();

            command = readCommand();

            if (command > 5 || command < 1) {
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
            System.out.println("\n");
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

        gamesManager.startGame(home, away);
    }

    private String readTeam() {
        var team = scanner.nextLine();
        while (gamesManager.teamExists(team)) {
            team = scanner.nextLine();
        }

        return team;
    }

    private void finishGameCommand() {
        System.out.println("Select a game to complete: ");
        var games = gamesManager.getGames();
        printer.printGames(games, true);

        while (true) {
            var gameIdx = readCommand();
            if (gameIdx > games.size() + 1 || gameIdx <= 0) {
                System.out.println("undefined command!");
            } else {
                if (gameIdx < games.size() + 1) {
                    gamesManager.finishGame(gameIdx - 1);
                }
                break;
            }
        }
    }

    private void updateScoreCommand() {
        System.out.println("Select score upgrade game: ");
        var games = gamesManager.getGames();
        printer.printGames(games, true);

        while (true) {
            var gameIdx = readCommand();
            if (gameIdx == games.size() + 1) {
                break;
            }
            if (gameIdx > games.size() + 1 ||  gameIdx <= 0) {
                System.out.println("undefined command!");
            } else {
                if (gameIdx < games.size() + 1) {
                    gamesManager.updateScore(gameIdx - 1);
                }
                break;
            }
        }
    }

    private int readCommand() {
        int idx  = -1;
        while (true){
            try {
                idx = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("undefined command, try again..");
            }
        }

        return idx;
    }

    private void printSummaryOfGamesByTotalScore() {
        printer.printGames(gamesManager.getSummaryOfGamesByTotalScore(), false);
    }
}
