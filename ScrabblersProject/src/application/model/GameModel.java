package application.model;

import application.engine.GameEngine;

public class GameModel {
	
	public static void endTurn() {
		GameEngine.getPlayer().getHand().clearHand();
		GameEngine.refillHand();
		GameEngine.refreshTray();
	}  
	
	public static void undoMoves() {
		GameEngine.returnHand();
	}
	
}
