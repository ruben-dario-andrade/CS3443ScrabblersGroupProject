package application.engine;

import java.util.LinkedList;

import application.components.GamePlayerTray;

public class EngineTray {
	private LinkedList<String> hand;
	GamePlayerTray gamePlayerTray;
	
	public EngineTray(GamePlayerTray gamePlayerTray) {
		this.hand = new LinkedList<String>();
		this.gamePlayerTray = gamePlayerTray;
	}
	
	public void refreshTray() {
		gamePlayerTray.addRefreshHand(hand);
	}
	
	public void addPiece(String letter) {
		if (hand.size() <= 7) {
			hand.add(letter);
		}
	}
	
	public void removePiece(String letter) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).equals(letter)) {
				hand.add(i, " ");
				hand.remove(i+1);
				break;
			}
		}
		
	}
	
	public void clearHand() {
		for (int i = 0; i < hand.size() ; i++) {
			if (hand.get(i).charAt(0) == ' ') {
				hand.remove(i);
				i--;
			}
		}
	}
	
	public LinkedList<String> getList(){
		return this.hand;
	}
	
	
	public int getHandSize() {
		return hand.size();
	}

	
	
}
