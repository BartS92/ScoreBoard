package com.example.scoreboard.service.game;

import com.example.scoreboard.domain.Game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class GamesManagerImpl implements GamesManager {

    private List<Game> games = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);


    public  void startGame(String home, String away) {
        var game = new Game(home, away);
        games.add(game);
        System.out.println("New Game has been created");
        System.out.println(String.format("%s - %s: %d - %d", game.getHome().getName(), game.getAway().getName(), game.getHome().getScore(), game.getAway().getScore()));
    }


    public void finishGame(int gameIdx) {
        var game = games.get(gameIdx);
        var home = game.getHome();
        var away = game.getAway();
        System.out.println(String.format("Game %s - %s: %d - %d has been finished", home.getName(), away.getName(), home.getScore(), away.getScore()));
        games.remove(game);
    }

    public void updateScore(int gameId) {
        var game = games.get(gameId);
        var home = game.getHome();
        var away = game.getAway();

        home.setScore(GameUtils.generateScore(home.getScore()));
        away.setScore(GameUtils.generateScore(away.getScore()));
        System.out.println("New result: ");
        System.out.println(String.format("%s - %s: %d - %d", home.getName(), away.getName(), home.getScore(), away.getScore()));
    }



    public List<Game> getSummaryOfGamesByTotalScore() {
        var sorted = new ArrayList<Game>(games);
        Collections.sort(sorted);
        return sorted;
    }

    public boolean teamExists(String teamName) {
        var existRes =  games.stream().anyMatch(g -> g.getHome().getName().equals(teamName) || g.getAway().getName().equals(teamName));
        if (existRes){
            System.out.println(String.format("The team %s is already playing, write another name", teamName));
        }
        return existRes;
    }

    @Override
    public List<Game> getGames() {
        return games;
    }


    @Override
    public void close() throws Exception {
        scanner.close();
    }


}
