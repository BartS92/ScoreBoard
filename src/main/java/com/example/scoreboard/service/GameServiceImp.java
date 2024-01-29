package com.example.scoreboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImp extends GameService {
    @Override
    public void update() {
        var homeTeam = super.getGame().getHome();
        var awayTeam = super.getGame().getAway();

        var homeScore = GameUtils.generateScore(homeTeam.getScore());
        var awayScore = GameUtils.generateScore(awayTeam.getScore());
        homeTeam.setScore(homeScore);
        awayTeam.setScore(awayScore);
    }
}
