package com.games.ticTacToe.controllers;

import com.games.ticTacToe.AI.AlphaBetaAdvanced;
import com.games.ticTacToe.AI.Simple;
import com.games.ticTacToe.model.Board;
import com.games.ticTacToe.view.ConsoleView;

public class GameController {

    private static Board board;
    private static ConsoleView view;

    private final int EASY_DIFFICULT_CODE = 1;
    private final int HARD_DIFFICULT_CODE = 2;
    private final int boardWidth;

    private final Board.State FIGURE_PLAYER = Board.State.X;

    public GameController() {
        board = new Board();
        view = new ConsoleView();
        boardWidth = Board.getBoardWidth();
    }

    public static Board getBoard() {
        return board;
    }

    public void play() {

        int difficult = view.startGame();

        while (!board.isGameOver()) {
            view.printGameStatus();
            playMove(difficult);
        }

        Board.State winner = board.getWinner();
        view.printWinner(winner);
    }

    private void playMove(int codeDifficult) {
        if (board.getTurn() == FIGURE_PLAYER) {
            getCoordinates();
        } else {
            switch (codeDifficult){
                case EASY_DIFFICULT_CODE:
                    Simple.run(board);
                    break;
                case HARD_DIFFICULT_CODE:
                    AlphaBetaAdvanced.run(board);
                    break;
                default:
                    break;
            }

        }
    }

    private void getCoordinates() {
        int[] coordinates = view.getCoordinates();

        String error = "";
        if (!checkCoordinates(coordinates, boardWidth)) {

            error = "\nInvalid move." +
                    "\nThe index of the move must be between 0 and " +
                    (Board.getBoardWidth()-1) +
                    ", inclusive.";

        } else if (!board.move(coordinates[0], coordinates[1])) {

            error = "\nInvalid move." +
                    "\nThe selected coordinates must be blank.";
        }

        view.printError(error);
    }

    private boolean checkCoordinates(int[] coordinates, int boardWidth) {

        if (coordinates[0] >= 0 && coordinates[0] <= boardWidth-1) {
            return coordinates[1] >= 0 && coordinates[1] <= boardWidth-1;
        }

        return false;
    }
}
