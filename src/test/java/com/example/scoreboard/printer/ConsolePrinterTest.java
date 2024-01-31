package com.example.scoreboard.printer;


import com.example.scoreboard.domain.Game;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class ConsolePrinterTest {

    @Autowired
    private Printer testObj;

    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void printGames_whenGivenGames_thenGamesPrinted() {
        // when
        var game1 = new Game("Test1", "Test2");
        var game2 = new Game("Test3", "Test4");

        // then
        testObj.printGames(new ArrayList<>(){{add(game1);}{add(game2);}}, false);


        assertTrue(outContent.toString().contains(String.format("%d. %s - %s: %d - %d",1, game1.getHome().getName(), game1.getAway().getName(), 0 ,0)));
        assertTrue(outContent.toString().contains(String.format("%d. %s - %s: %d - %d",2, game2.getHome().getName(), game2.getAway().getName(), 0 ,0)));
    }
}
