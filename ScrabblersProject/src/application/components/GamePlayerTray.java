package application.components;

import java.util.LinkedList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class GamePlayerTray extends GridPane{
	
	private LinkedList<String> hand;
	
	public GamePlayerTray() { 
		
		this.hand = new LinkedList<String>();
		
		Border border = new Border(new BorderStroke(null,
				BorderStrokeStyle.SOLID,
				new CornerRadii(0),
				new BorderWidths(1)
		));
		this.setBorder(border);
		this.setMaxSize(500, 60);
		this.setPrefSize(500, 60);
		this.setMinSize(500, 60);
		this.setPadding(new Insets(1, 1, 0 ,0));
		
		this.add(new GamePiece('A', true, -1, -1), 1, 0);
		//this.setAlignment(Pos.BASELINE_RIGHT);
	}
	
	public void addPiece(char letter) {
		if (hand.size() <= 8) {
			this.hand.add(letter+"");
			for (int i = 0; i < hand.size(); i++) {
				this.add(new GamePiece(hand.get(i).charAt(0), true, -1, -1), i + 1, 0);
			}
		}
		
	}
	
	public void removePiece(char letter) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).charAt(0) == letter) {
				hand.remove(i);
			}
		}
		this.addPiece(' ');
	}
	
	public LinkedList<String> getPlayerHand(){
		return this.hand;
	}
	
}
