package application.game;

public class Player {
	private Hand hand;
	private int points;
	
	public Player() {
		this.hand = new Hand();
		this.points = 0;
	}
	
	public Hand getHand() {
		return this.hand;
	}

	public int getHandSize() {
		return hand.getList().size();
	}

	
	
}
