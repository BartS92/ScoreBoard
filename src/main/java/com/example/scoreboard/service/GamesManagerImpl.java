package com.example.scoreboard.service;

import com.example.scoreboard.domain.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.IntStream;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class GamesManagerImpl implements  GamesManager {

    private List<Game> games = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);


    public  void startGame() {
        String home = "";
        String away = "";
        while (away.isEmpty()  || home.isEmpty() || away.equalsIgnoreCase(home)){
            System.out.println("Write a name of the a home team");
            home = addTeam();

            System.out.println("Write a name of the a away team");
            away = addTeam();
            if (away.equalsIgnoreCase(home)) {
                System.out.println("Teams names should be different, try again");
            }
        }
        var game = new Game(home, away);
        games.add(game);
        System.out.println("New Game has been created");
        System.out.println(String.format("%s - %s: %d - %d", game.getHome().getName(), game.getAway().getName(), game.getHome().getScore(), game.getAway().getScore()));
    }

    private String addTeam(){
        var team = scanner.next();
        while (teamExists(team)) {
            team = scanner.next();
        }

        return team;
    }

    private boolean teamExists(String teamName) {
        var existRes =  games.stream().anyMatch(g -> g.getHome().getName().equals(teamName) || g.getAway().getName().equals(teamName));
        if (existRes){
            System.out.println(String.format("The team %s is already playing, write another name", teamName));
        }
        return existRes;
    }


    public void finishGame() {
        System.out.println("Select a game to complete: ");

        printGames(games);

        while (true) {
            var gameNumber = scanner.nextInt() - 1;
            if (gameNumber > games.size()) {
                System.out.println("Wrong game id, try again!");
            } else {
                var game = games.get(gameNumber);
                var home = game.getHome();
                var away = game.getAway();
                System.out.println(String.format("Game %s - %s: %d - %d has been finished", home.getName(), away.getName(), home.getScore(), away.getScore()));
                games.remove(game);
                break;
            }
        }
    }

    public void updateScore() {
        System.out.println("Select score upgrade game: ");
        printGames(games);

        while (true) {
            var gameNumber = scanner.nextInt() - 1;
            if (gameNumber > games.size()) {
                System.out.println("Wrong game id, try again!");
            } else {
                var game = games.get(gameNumber);
                var home = game.getHome();
                var away = game.getAway();

                home.setScore(GameUtils.generateScore(home.getScore()));
                away.setScore(GameUtils.generateScore(away.getScore()));
                System.out.println("New result: ");
                System.out.println(String.format("%s - %s: %d - %d", home.getName(), away.getName(), home.getScore(), away.getScore()));
                break;
            }
        }
    }

    private void printGames(List<Game> games) {
        IntStream.range(0, games.size()).forEach(idx -> {
            var game = games.get(idx);
            var home =  game.getHome();
            var away =  game.getAway();
            System.out.println(String.format("%d. %s - %s: %d - %d",idx, home.getName(), away.getName(), home.getScore(),  away.getScore()));
        });
    }

    public void getSummaryOfGamesByTotalScore() {
        var t = new TreeSet(games);
        printGames(t.stream().toList());
    }


    @Override
    public void close() throws Exception {
        scanner.close();
    }
}
