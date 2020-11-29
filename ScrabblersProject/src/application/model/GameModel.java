package application.model;

import java.util.LinkedList;

import application.engine.GameEngine;

public class GameModel {
	
	public static String endTurn() {
		if(!GameEngine.getIsRefreshing()) {
			if (GameEngine.checkValid()) {
				GameEngine.getTray().clearHand();
				GameEngine.refillHand();
				GameEngine.resetTray();
				GameEngine.clearUsedPieces();
				return "Valid word! Your/Opponent's move.";
			} else {
				GameEngine.returnHand();
				GameEngine.returnBoard();
				return "Invalid word. Please try again.";
			}
		} else {
			GameEngine.commitRefresh();
			GameEngine.getTray().clearHand();
			GameEngine.refillHand();
			GameEngine.resetTray();
			GameEngine.clearUsedPieces();
			return "New pieces placed into hand.";
		}
		
	}  
	
	public static void undoMoves() {
		if(!GameEngine.getIsRefreshing()) {
			GameEngine.returnHand();
			GameEngine.returnBoard();
		} else {
			GameEngine.undoRefresh();
		}
	}
	
	public static String refreshLetters() {
		if (GameEngine.getIsRefreshing()) {
			return GameEngine.setIsRefreshing(false);
		} else {
			return GameEngine.setIsRefreshing(true);
		}
	}
	
}
