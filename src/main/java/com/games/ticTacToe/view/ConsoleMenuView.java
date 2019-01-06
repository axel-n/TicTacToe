package com.games.ticTacToe.view;

import com.games.ticTacToe.model.exceptions.InvalidBoardSizeException;
import com.games.ticTacToe.controller.Game;
import com.games.ticTacToe.controller.GameStarter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleMenuView {

    private static final int PLAYER_WITH_AI_CODE = 1;
    private static final int PLAYER_WITH_PLAYER_CODE = 2;

    private static final int START_CODE = 1;

    private static final int SETTINGS_CODE = 2;

    private static int BOARD_SIZE;

    private static final int MIN_SIZE = 3;

    public static void showMenuTypePlayers() {
        System.out.printf("%nQuestion 1 of 3. Who will be play?%n");
        System.out.println(PLAYER_WITH_AI_CODE + " - Player with AI");
        System.out.println(PLAYER_WITH_PLAYER_CODE + " - Player with Player");

        try {
            final Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case PLAYER_WITH_AI_CODE:
                    showMenuForPlayers2AI();
                    break;
                case PLAYER_WITH_PLAYER_CODE:
                    showMenuForPlayers2Players();
                    break;
                default:
                    System.out.println("Choice is incorrect, please try again");
                    showMenuTypePlayers();
                    break;
            }
        } catch (final InputMismatchException e) {
            System.out.println("Please enter correct choice");
            showMenuTypePlayers();
        }
    }

    private static void showMenuForPlayers2AI() {
        System.out.printf("%nQuestion 2 of 3.%n");
        customInput(1).theGame();
    }

    private static void showMenuForPlayers2Players() {
        System.out.println("Question 2 of 3.");
        System.out.println(START_CODE + " - Play with preset players name");
        System.out.println(SETTINGS_CODE + " - Set up and play");

        try {
            final Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case START_CODE:
                    System.out.println("A new game started");
                    GameStarter.defaultStart().theGame();
                    break;
                case SETTINGS_CODE:
                    customInput(2).theGame();
                    break;
                default:
                    System.out.println("Choice is incorrect, please try again");
                    showMenuForPlayers2Players();
                    break;
            }
        } catch (final InputMismatchException e) {
            System.out.println("Please enter correct choice");
            showMenuForPlayers2Players();
        }

    }

    private static Game customInput(int countPlayers) {
        Scanner input = new Scanner(System.in);
        final String gameName = "XO";
        String playerOneName, playerTwoName; ;

        System.out.printf("Your name? ");
        playerOneName = input.nextLine();

        if (countPlayers > 1) {
            System.out.printf("Enter player two name: ");
            playerTwoName = input.nextLine();
        } else {
            System.out.println("Player 2 name: AI");
            playerTwoName = "AI";
        }

        final int boardSize = enterSize();
        return GameStarter.customStart(boardSize, playerOneName, playerTwoName, gameName);
    }

    public static int enterSize() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.printf("%nQuestion 3 of 3. Size of board? ");
            BOARD_SIZE = input.nextInt();
            if (BOARD_SIZE < MIN_SIZE) {
                throw new InvalidBoardSizeException();
            }
        } catch (final InputMismatchException | InvalidBoardSizeException e) {
            System.out.println("Input is wrong, please enter correct integer greater than 3");
            enterSize();
        }
        return BOARD_SIZE;
    }

}
