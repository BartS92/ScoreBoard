package com.example.scoreboard.printer;

import com.example.scoreboard.domain.Game;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.Component;

@Component
public class ConsolePrinter implements Printer {
    @Override
    public void printGames(List<Game> games, boolean backOption) {
        IntStream.range(0, games.size()).forEach(idx -> {
            var game = games.get(idx);
            var home = game.getHome();
            var away = game.getAway();
            System.out.println(String.format("%d. %s - %s: %d - %d", idx + 1, home.getName(), away.getName(), home.getScore(), away.getScore()));
        });
        if (backOption) {
            System.out.println(String.format("%d. Back", games.size() + 1));
        }
    }

    @Override
    public void printMenu() {
        System.out.println("Please, select a command:");
        System.out.println("1. Start a game");
        System.out.println("2. Finish a game");
        System.out.println("3. Update score");
        System.out.println("4. Get a summary of games by total score");
        System.out.println("5. Exit");
    }
}
