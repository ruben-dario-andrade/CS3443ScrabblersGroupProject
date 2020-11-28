package application.model;

import java.util.LinkedList;

import application.engine.GameEngine;

public class GameModel {
	
	public static void endTurn() {
		if (GameEngine.checkValid()) {
			GameEngine.getTray().clearHand();
			GameEngine.refillHand();
			GameEngine.refreshTray();
			GameEngine.clearUsedPieces();
		} else {
			GameEngine.returnHand();
			GameEngine.returnBoard();
		}
	}  
	
	public static void undoMoves() {
		GameEngine.returnHand();
		GameEngine.returnBoard();
	}
	
}
