package com.games.ticTacToe.AI;

import com.games.ticTacToe.model.Board;

public class Simple {

    private static int boardLength;

    public static void run(Board board) {

        boardLength = Board.getBoardWidth();
        simple(board);
    }

    private static void simple(Board board) {

        outerloop:
        for (int y = 0; y < boardLength; y++) {
            for (int x = 0; x < boardLength; x++) {

                if (board.getBoard()[x][y] == Board.State.Blank) {
                    board.move(x, y);
                    break outerloop;
                }
            }
        }
    }


}
