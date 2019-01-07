package com.games.ticTacToe.AI;

import com.games.ticTacToe.model.Board;

public class Algorithms {

    private Algorithms() {}


    public static void alphaBetaAdvanced (Board board) {
        AlphaBetaAdvanced.run(board.getTurn(), board, Double.POSITIVE_INFINITY);
    }

}
