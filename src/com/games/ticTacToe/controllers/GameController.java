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

    public void play () {

        System.out.println("Starting a new game.");

        while (true) {
            printGameStatus();
            playMove();

            if (board.isGameOver()) {
                printWinner();

                if (!tryAgain()) {
                    break;
                }
            }
        }
    }

    private void playMove () {
        if (board.getTurn() == Board.State.X) {
            getPlayerMove();
        } else {
            Algorithms.alphaBetaAdvanced(board);
        }
    }

    private void printGameStatus () {
        System.out.println("\n" + board + "\n");
        System.out.println(board.getTurn().name() + "'s turn.");
    }

    private void getPlayerMove () {
        view.getPlayerMove();
    }

    private void printWinner () {
        Board.State winner = board.getWinner();

        System.out.println("\n" + board + "\n");

        if (winner == Board.State.Blank) {
            System.out.println("The TicTacToe is a Draw.");
        } else {
            System.out.println("Player " + winner.toString() + " wins!");
        }
    }

    private boolean tryAgain () {
        if (promptTryAgain()) {
            board.reset();
            view.againText();
            return true;
        }

        return false;
    }

    private boolean promptTryAgain () {
        return view.promtAgain();
    }
}
