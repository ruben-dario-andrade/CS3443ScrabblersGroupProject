package application.model;

import application.engine.GameEngine;

public class GameModel {
	
	public static void endTurn() {
		GameEngine.getPlayer().clearHand();
		GameEngine.refillHand();
		GameEngine.refreshTray();
		GameEngine.clearUsedPieces();
	}  
	
	public static void undoMoves() {
		GameEngine.returnHand();
		GameEngine.returnBoard();
	}
	
}
