package application.game;

import java.util.LinkedList;

public class Hand {
	
	private LinkedList<String> hand;
	
	public Hand() {
		this.hand = new LinkedList<String>();
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
	
}