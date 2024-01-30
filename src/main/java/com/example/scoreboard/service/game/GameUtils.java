package com.example.scoreboard.service.game;

import java.util.Random;

public class GameUtils {
    GameUtils(){}

    private static final Random rand = new Random();
    private static final Integer MAX_SCORE = 10;

    public static int generateScore(int initScore) {
        return rand.nextInt(MAX_SCORE - initScore + 1) + initScore;
    }
}
