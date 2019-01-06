package com.games.ticTacToe.view;

import com.games.ticTacToe.controller.GameController;
import com.games.ticTacToe.model.Player;
import com.games.ticTacToe.model.Point;

public interface IView {

    public Point startTurn(Player currentPlayer);

    public void showGameName();

    public void showPlayers();

    public void showBoard();

    public void showWinner();

    public void showDraw();

    public void anotherGame();

    public void showPointOccupied();

    public GameController getGameController();

}
