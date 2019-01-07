package com.games.ticTacToe.controllers;

import com.games.ticTacToe.AI.Algorithms;
import com.games.ticTacToe.model.Board;
import com.games.ticTacToe.view.ConsoleView;

public class GameController {

    private static Board board;
    private static ConsoleView view;

    public GameController() {
        board = new Board();
        view = new ConsoleView();
    }

    public static Board getBoard() {
        return board;
    }

    public void play() {

        view.startGame();

        while (true) {
            view.printGameStatus();
            playMove();

            if (board.isGameOver()) {

                Board.State winner = board.getWinner();
                view.printWinner(winner);

                if (!tryAgain()) {
                    break;
                }
            }
        }
    }

    private void playMove() {
        if (board.getTurn() == Board.State.X) {
            getPlayerMove();
        } else {
            Algorithms.alphaBetaAdvanced(board);
        }
    }

    private void getPlayerMove() {
        int moveIndex = view.getPlayerMove();

        String error = "";
        if (moveIndex < 0 || moveIndex >= Board.getBoardWidth() * Board.getBoardWidth()) {

            error = "\nInvalid move." +
                    "\nThe index of the move must be between 0 and " +
                    (Board.getBoardWidth() * Board.getBoardWidth() - 1) +
                    ", inclusive.";

        } else if (!board.move(moveIndex)) {

            error = "\nInvalid move." +
                    "\nThe selected index must be blank.";
        }

        view.printError(error);
    }


    private boolean tryAgain() {
        if (promptTryAgain()) {
            board.reset();
            view.againText();
            return true;
        }

        return false;
    }

    private boolean promptTryAgain() {
        return view.promtAgain();
    }
}
