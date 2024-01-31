package com.example.scoreboard.service;


import com.example.scoreboard.domain.Game;
import com.example.scoreboard.service.game.GamesManager;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class GamesManagerTest {

    @Autowired
    private GamesManager testObj;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        testObj.clear();
    }

    @Test
    void startGame_whenGiveValidTeams_thenGameCreated() {
        // when
        String home = "Team 1";
        String away = "Team 2";

        // then
        testObj.startGame(home, away);

        assertEquals(1, testObj.getGames().size());
        var game = testObj.getGames().get(0);
        assertEquals("Team 1", game.getHome().getName());
        assertEquals("Team 2", game.getAway().getName());

        assertEquals(0, game.getHome().getScore());
        assertEquals(0, game.getAway().getScore());
    }

    @Test
    void finishGame_whenGiveValidGame_thenGameRemoved() {
        // when
        String home = "Team 1";
        String away = "Team 2";
        testObj.startGame(home, away);

        assertEquals(1, testObj.getGames().size());

        // then
        testObj.finishGame(0);
        assertTrue(outContent.toString().contains("Game Team 1 - Team 2: 0 - 0 has been finished"));

    }

    @Test
    void updateScore_whenGiveValidGame_thenReturnedGameWithUpdateScore() {
        // when
        String home = "Team 1";
        String away = "Team 2";
        testObj.startGame(home, away);


        assertEquals(1, testObj.getGames().size());

        // then
        testObj.updateScore(0);
        var game  = testObj.getGames().get(0);
        assertNotEquals(0, game.getAway().getScore() + game.getHome().getScore());
    }

    @Test
    void getSummaryOfGamesByTotalScore_whenGamesExist_thenReturnedSortedGamesByScore() {
        // when
        String home = "Team 1";
        String away = "Team 2";
        testObj.startGame(home, away);

        home = "Team 3";
        away = "Team 4";
        testObj.startGame(home, away);

        testObj.updateScore(1);


        var game1 = testObj.getGames().get(0);
        var game2 = testObj.getGames().get(1);
        var sorted = testObj.getSummaryOfGamesByTotalScore();
        assertEquals(new ArrayList<Game>(){
            {add(game2);}
            {add(game1);}
        }, sorted);
    }

    @Test
    void teamExists_whenTeamPlaying_thenReturnedTrue() {
        // when
        var home = "Team 3";
        var away = "Team 4";
        testObj.startGame(home, away);

        //then
        assertTrue(testObj.teamExists("Team 3"));
    }
}
