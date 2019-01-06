package com.games.ticTacToe.model;

public class Player {

    private final String name;
    private final Figure figure;
    private final Type type;

    public Player(final String name, final Figure figure, Type type) {
        assert name != null;
        assert figure != null;

        this.name = name;
        this.figure = figure;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Figure getFigure() {
        return figure;
    }

    public enum Type {
        Player, AI
    }

}