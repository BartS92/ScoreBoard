package com.example.scoreboard.domain;

import lombok.Getter;
import lombok.Setter;


@Getter
public class Team {
    private final String name;
    @Setter
    private int score;
    public Team (String name){
        this.name = name;
    }
}
