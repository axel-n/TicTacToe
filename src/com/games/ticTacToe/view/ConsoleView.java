package com.games.ticTacToe.view;

import com.games.ticTacToe.controllers.GameController;
import com.games.ticTacToe.model.Board;

import java.util.Scanner;

public class ConsoleView {

    private static Board board;

    private final int EASY_DIFFICULT_CODE = 1;
    private final int HARD_DIFFICULT_CODE = 2;

    private Scanner sc = new Scanner(System.in);
    private int[] coordinates;


    public ConsoleView() {
        board = GameController.getBoard();
    }

    public int printChoiceDifficult() {
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
                printChoiceDifficult();
                break;
        }

        return 0;

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

    public int[] getCoordinates() {
        System.out.println("Enter coordinates for figure (X Y): ");

        sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        String[] inputUserRow = userInput.split(" ");

        if (FacadeValidationCoordinates(inputUserRow)) {
            return coordinates;
        } else getCoordinates();

        return coordinates;
    }

    private boolean FacadeValidationCoordinates(String[] inputUserRow) {

        if (checkStringWithCoordinates(inputUserRow)) {
            coordinates = new int[2];
            coordinates = parseCoordinates(inputUserRow);
            return isValidCoordinates(coordinates);
        }
        return false;
    }

    private boolean isValidCoordinates(int[] coordinates) {

        int x = coordinates[0];
        int y = coordinates[1];

        String error;
        if (x < 0 || x > Board.getBoardWidth() - 1 || y < 0 || y > Board.getBoardWidth() - 1) {

            error = "\nInvalid coordinates." +
                    "\nThey must be between 0 and " +
                    (Board.getBoardWidth() - 1) +
                    ", inclusive.";

            printError(error);
            return false;

        } else if (board.getBoard()[x][y] != Board.State.Blank) {
            error = "\nInvalid coordinates. " +
                "\nFigure exist on board";

            printError(error);
            return false;

        }

        return true;
    }

    private void printError(String message) {
        System.out.println(message);
    }

    private int[] parseCoordinates(String[] inputUserRow) {

        coordinates[0] = Integer.parseInt(inputUserRow[0]);
        coordinates[1] = Integer.parseInt(inputUserRow[1]);

        return coordinates;
    }

    private boolean checkStringWithCoordinates(String[] inputUserRow) {

        if (inputUserRow.length == 2) {
            try {
                Integer.parseInt(inputUserRow[0]);
                Integer.parseInt(inputUserRow[1]);
                return true;
            } catch (NumberFormatException nfe) {
                String error = "\nInvalid coordinates." +
                        "\nMust be X and Y (example: 0 0)";

                printError(error);

                return false;
            }
        }

        String error = "\nInvalid coordinates." +
                "\nMust be X and Y (example: 0 0)";

        printError(error);

        return false;
    }
}
