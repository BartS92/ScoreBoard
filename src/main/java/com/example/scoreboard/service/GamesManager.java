package com.example.scoreboard.service;

public interface GamesManager extends AutoCloseable{
    void startGame();

    void finishGame();

    void updateScore();
    void getSummaryOfGamesByTotalScore();
}
