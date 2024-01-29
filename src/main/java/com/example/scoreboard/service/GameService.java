package com.example.scoreboard.service;


import com.example.scoreboard.domain.Game;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class GameService {

    private Game game;
    public void start() {

    }

    public abstract void update();
    public void finish() {

    }
}
