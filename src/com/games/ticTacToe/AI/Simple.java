package com.games.ticTacToe.AI;

import com.games.ticTacToe.model.Board;

public class Simple {

    private static int boardLength;

    public static void run(Board board) {

        boardLength = Board.getBoardWidth();
        simple(board);
    }

    private static void simple(Board board) {

        System.out.println("simple start");

        outerloop:
        for (int y = 0; y < boardLength; y++) {
            for (int x = 0; x < boardLength; x++) {

                System.out.printf("%ncoordinates x: %s, y: %s", x, y);
                System.out.printf("%nstate[x][y]: %s%n", board.getBoard()[x][y]);

                if (board.getBoard()[x][y] == Board.State.Blank) {
                    board.move(x, y);
                    break outerloop;
                }
            }
        }
    }


}
