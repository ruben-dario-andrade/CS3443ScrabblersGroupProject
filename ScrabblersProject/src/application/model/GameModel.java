package application.model;

import java.util.LinkedList;

import application.engine.GameEngine;

public class GameModel {
	
	public static boolean endTurn() {
		if (GameEngine.checkValid()) {
			GameEngine.getTray().clearHand();
			GameEngine.refillHand();
			GameEngine.refreshTray();
			GameEngine.clearUsedPieces();
			return true;
		} else {
			GameEngine.returnHand();
			GameEngine.returnBoard();
			return false;
		}
	}  
	
	public static void undoMoves() {
		GameEngine.returnHand();
		GameEngine.returnBoard();
	}
	
}
