package com.example.scoreboard.service;


import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class GamesManagerTest {

    @Autowired
    private GamesManager gamesManager;

    @Test
    void startGame_whenGiveValidRequest_thenCheckResults() {
        // when
        gamesManager.startGame();
        String input = Application.readName();
        assertEquals(NAME.concat("Baeldung"), input);


    }

    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
}
