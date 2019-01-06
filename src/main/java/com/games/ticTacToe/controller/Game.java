package com.games.ticTacToe.controller;

import com.games.ticTacToe.model.Player;
import com.games.ticTacToe.model.Point;
import com.games.ticTacToe.model.exceptions.InvalidPointException;
import com.games.ticTacToe.model.exceptions.PointOccupiedException;
import com.games.ticTacToe.view.IView;

public class Game {

    final private IView iview;
    final private GameController gameController;

    Game(IView iview) {
        this.iview = iview;
        this.gameController = iview.getGameController();
    }

    public void theGame() {
        while (gameController.getNextTurn()) {

            Player currentPlayer = gameController.getCurrentPlayer(gameController.getPlayers()[0]);

            if (currentPlayer.getType() == Player.Type.AI) {

                stepAi(currentPlayer);

            } else {

                Point point = iview.startTurn(currentPlayer);

                try {

                    gameController.move(point.getX(), point.getY(), currentPlayer);
                } catch (PointOccupiedException e) {
                    iview.showPointOccupied();
                }
            }


            iview.showBoard();
        }
        if (gameController.getWinner() != null) {
            iview.showWinner();
        } else {
            iview.showDraw();
        }
        iview.anotherGame();
    }

    GameController getGameController() {
        return gameController;
    }

    private void stepAi(Player currentPlayer) {

        int boardLength = gameController.getBoard().getFiguresArray().length;

        outerloop:
        for (int x = 0; x < boardLength; x++) {
            for (int y = 0; y < boardLength; y++) {
                try {
                    if (gameController.getBoard().getFigure(x, y) == null) {
                        gameController.move(x, y, currentPlayer);
                        break outerloop;
                    }

                } catch (InvalidPointException | PointOccupiedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
