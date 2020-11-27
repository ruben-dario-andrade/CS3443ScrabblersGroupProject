package application.engine;

import java.util.LinkedList;

public class EnginePlayer {
	private LinkedList<String> hand;
	private int points;
	
	
	
	public EnginePlayer() {
		this.hand = new LinkedList<String>();
		this.points = 0;
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
