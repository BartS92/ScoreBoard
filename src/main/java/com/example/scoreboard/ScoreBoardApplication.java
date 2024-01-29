package com.example.scoreboard;

import com.example.scoreboard.service.GamesManager;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScoreBoardApplication  implements CommandLineRunner {

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private GamesManager gamesManager;

    public static void main(String[] args) {
        SpringApplication.run(ScoreBoardApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started");
        var command = 0;
        while (command != 5) {
            printMenu();

            try{
                command = Integer.parseInt(scanner.next());
            }
            catch (NumberFormatException e){
                System.out.println("undefined command, try again..");
                continue;
            }

            if (command > 5){
                System.out.println("undefined command, try again..");
                continue;
            }

            switch (command) {
                case 1: {
                    gamesManager.startGame();
                    break;
                }
                case 2: {
                    gamesManager.finishGame();
                    break;
                }
                case 3: {
                    gamesManager.updateScore();
                    break;
                }
                case 4: {
                    gamesManager.getSummaryOfGamesByTotalScore();
                }
            }
            System.out.println("\n\n");
        }
        System.out.println("Closing application..");
        scanner.close();
    }

    private void printMenu() {
        System.out.println("Please, select a command:");
        System.out.println("1. Start a game");
        System.out.println("2. Finish a game");
        System.out.println("3. Update score");
        System.out.println("4. Get a summary of games by total score");
        System.out.println("5. Exit");
    }


}
