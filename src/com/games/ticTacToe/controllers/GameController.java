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


    private final Board.State FIGURE_PLAYER = Board.State.X;

    public GameController() {
        board = new Board();
        view = new ConsoleView();
    }

    public static Board getBoard() {
        return board;
    }

    public void play() {

        int difficult = view.printChoiceDifficult();

        while (!board.isGameOver()) {
            view.printGameStatus();
            playMove(difficult);
        }

        Board.State winner = board.getWinner();
        view.printWinner(winner);
    }

    private void playMove(int codeDifficult) {
        if (board.getTurn() == FIGURE_PLAYER) {
            stepHuman();
        } else {
            stepAI(codeDifficult);
        }
    }

    private void stepAI(int codeDifficult) {
        switch (codeDifficult) {
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

    private void stepHuman() {
        int[] coordinates =  view.getCoordinates();
        board.move(coordinates[0], coordinates[1]);
    }
}
