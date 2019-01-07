package com.games.ticTacToe.view;

import com.games.ticTacToe.controllers.GameController;
import com.games.ticTacToe.model.Board;

import java.util.Scanner;

public class ConsoleView {

    public ConsoleView() {
        board = GameController.getBoard();
    }

    private static Board board;

    private Scanner sc = new Scanner(System.in);

    public final void againText() {
        System.out.println("Started new game.");
        System.out.println("X's turn.");
    }

    public boolean promtAgain(){
        while (true) {
            System.out.print("Would you like to start a new game? (Y/N): ");
            String response = sc.next();
            if (response.equalsIgnoreCase("y")) {
                return true;
            } else if (response.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("Invalid input.");
        }
    }

    public void getPlayerMove() {
        System.out.print("Index of move: ");

        int move = sc.nextInt();

        if (move < 0 || move >= Board.getBoardWidth() * Board.getBoardWidth()) {
            System.out.println("\nInvalid move.");
            System.out.println("\nThe index of the move must be between 0 and "
                    + (Board.getBoardWidth() * Board.getBoardWidth() - 1) + ", inclusive.");
        } else if (!board.move(move)) {
            System.out.println("\nInvalid move.");
            System.out.println("\nThe selected index must be blank.");
        }
    }
}
