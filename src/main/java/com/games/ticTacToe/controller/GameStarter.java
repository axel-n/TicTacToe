package com.games.ticTacToe.controller;

import com.games.ticTacToe.model.Board;
import com.games.ticTacToe.model.Figure;
import com.games.ticTacToe.model.Player;
import com.games.ticTacToe.model.Player.Type;
import com.games.ticTacToe.model.exceptions.InvalidBoardSizeException;
import com.games.ticTacToe.view.ConsoleView;

public class GameStarter {

    public static Game defaultStart(){
        final int boardSize = 3;
        final String gameName = "XO";
        final String playerOneName = "PLAYER X";
        final String playerTwoName = "PLAYER O";
        return customStart(boardSize,playerOneName,playerTwoName, gameName);
    }

    public static Game customStart(final int BOARD_SIZE, final String playerOneName, final String playerTwoName, final String gameName){
        Board board = null;
        try {
            board = new Board(BOARD_SIZE);
        }
        catch (InvalidBoardSizeException e){
            e.printStackTrace();
        }
        final Player[] players = new Player[2];
        players[0] = new Player(playerOneName, Figure.X, Type.Player);

        if (playerTwoName.equals("AI")) {
            players[1] = new Player(playerTwoName, Figure.O, Type.AI);
        } else {
            players[1] = new Player(playerTwoName, Figure.O, Type.Player);
        }

        final GameController gameController = new GameController(gameName, players, board);
        final ConsoleView consoleView = new ConsoleView(gameController);
        final Game game = new Game(consoleView);
        consoleView.showPlayers();
        return game;
    }
}
