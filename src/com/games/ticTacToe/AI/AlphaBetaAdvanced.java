package com.games.ticTacToe.AI;

import com.games.ticTacToe.model.Board;

public class AlphaBetaAdvanced {

    private static final int BOARD_WIDTH = Board.getBoardWidth();

    private static double maxPly;

    public static void run(Board board) {

        maxPly = Double.POSITIVE_INFINITY;

        alphaBetaPruning(board.getTurn(), board, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
    }

    private static int alphaBetaPruning(Board.State player, Board board, double alpha, double beta, int currentPly) {
        if (currentPly++ == maxPly || board.isGameOver()) {
            return score(player, board, currentPly);
        }

        if (board.getTurn() == player) {
            return getMax(player, board, alpha, beta, currentPly);
        } else {
            return getMin(player, board, alpha, beta, currentPly);
        }
    }

    private static int getMax(Board.State player, Board board, double alpha, double beta, int currentPly) {
        int indexOfBestMove = -1;

        for (Integer theMove : board.getAvailableMoves()) {

            Board modifiedBoard = board.getDeepCopy();

            modifiedBoard.move(theMove % BOARD_WIDTH, theMove / BOARD_WIDTH);
            int score = alphaBetaPruning(player, modifiedBoard, alpha, beta, currentPly);

            if (score > alpha) {
                alpha = score;
                indexOfBestMove = theMove;
            }

            if (alpha >= beta) {
                break;
            }
        }

        if (indexOfBestMove != -1) {
            board.move(indexOfBestMove % BOARD_WIDTH, indexOfBestMove / BOARD_WIDTH);
        }
        return (int) alpha;
    }


    private static int getMin(Board.State player, Board board, double alpha, double beta, int currentPly) {
        int indexOfBestMove = -1;

        for (Integer theMove : board.getAvailableMoves()) {

            Board modifiedBoard = board.getDeepCopy();
            modifiedBoard.move(theMove % BOARD_WIDTH, theMove / BOARD_WIDTH);

            int score = alphaBetaPruning(player, modifiedBoard, alpha, beta, currentPly);

            if (score < beta) {
                beta = score;
                indexOfBestMove = theMove;
            }

            if (alpha >= beta) {
                break;
            }
        }

        if (indexOfBestMove != -1) {
            board.move(indexOfBestMove % BOARD_WIDTH, indexOfBestMove / BOARD_WIDTH);
        }
        return (int) beta;
    }


    private static int score(Board.State player, Board board, int currentPly) {

        if (player == Board.State.Blank) {
            throw new IllegalArgumentException("Player must be X or O.");
        }

        Board.State opponent = (player == Board.State.X) ? Board.State.O : Board.State.X;

        if (board.isGameOver() && board.getWinner() == player) {
            return 10 - currentPly;
        } else if (board.isGameOver() && board.getWinner() == opponent) {
            return -10 + currentPly;
        } else {
            return 0;
        }
    }

}
