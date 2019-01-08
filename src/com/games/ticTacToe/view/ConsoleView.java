package com.games.ticTacToe.view;

import com.games.ticTacToe.controllers.GameController;
import com.games.ticTacToe.model.Board;

import java.util.Scanner;

public class ConsoleView {

    private static Board board;

    private final int EASY_DIFFICULT_CODE = 1;
    private final int HARD_DIFFICULT_CODE = 2;

    private Scanner sc = new Scanner(System.in);

    public ConsoleView() {
        board = GameController.getBoard();
    }

    public final void againText() {
        System.out.println("Started new game.");
        System.out.println("X's turn.");
    }

    public int startGame() {
        System.out.println("Starting a new game.");

        System.out.println("Choice diffucult:" +
                "\n1 - Easy" +
                "\n2 - Hard");

        switch (sc.nextInt()) {
            case EASY_DIFFICULT_CODE:
                return EASY_DIFFICULT_CODE;
            case HARD_DIFFICULT_CODE:
                return HARD_DIFFICULT_CODE;
            default:
                System.out.println("Incorrect code difficult");
                startGame();
                break;
        }

        return 0;

    }

    public int[] getCoordinates() {
        System.out.print("Enter coordinates for figure (X, Y): ");

        int[] result = {0, 0};
        result[0] = sc.nextInt();
        result[1] = sc.nextInt();

        return result;
    }

    public void printWinner(Board.State winner) {

        System.out.println("\n" + board + "\n");

        if (winner == Board.State.Blank) {
            System.out.println("The TicTacToe is a Draw.");
        } else {
            System.out.println("Player " + winner.toString() + " wins!");
        }
    }

    public void printGameStatus() {
        System.out.println("\n" + board + "\n");
        System.out.println(board.getTurn().name() + "'s turn.");
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
