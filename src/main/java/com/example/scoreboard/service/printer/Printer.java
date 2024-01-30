package com.example.scoreboard.service.printer;

import com.example.scoreboard.domain.Game;
import java.util.List;

public interface Printer {

    void printGames(List<Game> games, boolean backOption);
    void printMenu();
}
