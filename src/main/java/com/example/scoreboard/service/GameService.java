package com.example.scoreboard.service;


import com.example.scoreboard.domain.Game;
import com.example.scoreboard.domain.Status;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class GameService {

    private Game game;
    public void start() {
        game.setStatus(Status.STARTED);
    }

    public abstract void update();
    public void finish() {
        game.setStatus(Status.FINISHED);
    }
}
