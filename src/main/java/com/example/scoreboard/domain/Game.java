package com.example.scoreboard.domain;

import lombok.Getter;

@Getter
public class Game implements Comparable<Game> {
    private final Team home;
    private final Team away;

    public Game (String home, String away) {
        this.home = createTeam(home);
        this.away = createTeam (away);
    }

    private Team createTeam (String name){
        return new Team(name);
    }

    @Override
    public int compareTo(Game o) {
        return home.getScore() + away.getScore() - o.getHome().getScore() + o.getAway().getScore();
    }
}
