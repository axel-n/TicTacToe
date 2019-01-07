package com.games.ticTacToe.view;

import com.games.ticTacToe.controllers.GameController;
import com.games.ticTacToe.model.Board;

import java.util.Scanner;

public class ConsoleView {

    public void startGame(){
        System.out.println("Starting a new game.");

    }

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

    public int getPlayerMove() {
        System.out.print("Index of move: ");

        return sc.nextInt();
    }

    public void printWinner(Board.State winner) {

        System.out.println("\n" + board + "\n");

        if (winner == Board.State.Blank) {
            System.out.println("The TicTacToe is a Draw.");
        } else {
            System.out.println("Player " + winner.toString() + " wins!");
        }
    }

    public void printGameStatus () {
        System.out.println("\n" + board + "\n");
        System.out.println(board.getTurn().name() + "'s turn.");
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
