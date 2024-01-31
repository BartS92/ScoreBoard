package com.example.scoreboard.service.game;

import com.example.scoreboard.domain.Game;
import java.util.List;

public interface GamesManager {
    void startGame(String home, String away);

    void finishGame(int gameIdx);

    void updateScore(int gameIdx);

    List<Game> getSummaryOfGamesByTotalScore();

    boolean teamExists (String team);

    List<Game> getGames();

    void clear();

}
