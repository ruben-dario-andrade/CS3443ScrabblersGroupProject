package application.model;

import java.util.LinkedList;

import application.engine.GameEngine;

public class GameModel {
	
	public static int score;
	
	public static String endTurn() {
		score = 0;
		if(!GameEngine.getIsReplacing()) {
			score = GameEngine.checkValid();
			if (score != 0) {
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
			GameEngine.setIsReplacing(false);
			return "New pieces placed into hand.";
		}
		
	}  
	
	public static void undoMoves() {
		if(!GameEngine.getIsReplacing()) {
			GameEngine.returnHand();
			GameEngine.returnBoard();
		} else {
			GameEngine.undoRefresh();
		}
	}
	
	public static String refreshLetters() {
		if (GameEngine.getIsReplacing()) {
			return GameEngine.setIsReplacing(false);
		} else {
			return GameEngine.setIsReplacing(true);
		}
	}
	
}
