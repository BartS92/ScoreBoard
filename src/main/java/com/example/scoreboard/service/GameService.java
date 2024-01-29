package com.example.scoreboard.service;

import com.example.scoreboard.domain.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {
    private Game game;
    public void update() {
        var homeTeam = game.getHome();
        var awayTeam = game.getAway();

        var homeScore = GameUtils.generateScore(homeTeam.getScore());
        var awayScore = GameUtils.generateScore(awayTeam.getScore());
        homeTeam.setScore(homeScore);
        awayTeam.setScore(awayScore);
    }
}
