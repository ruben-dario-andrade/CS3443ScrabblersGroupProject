package application.components;

import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class GamePlayerTray extends GridPane{
	
	public GamePlayerTray() { 
		
		Border border = new Border(new BorderStroke(null,
				BorderStrokeStyle.SOLID,
				new CornerRadii(0),
				new BorderWidths(1)
		));
		this.setBorder(border);
		this.setMaxSize(280, 55);
		this.setPrefSize(280, 55);
		this.setMinSize(280, 55);
		this.setPadding(new Insets(1, 1, 0 ,0)); 
		
	}
	
	public void addHand(LinkedList<String> hand) {	
		for (int i = 0; i < hand.size(); i++) {
			this.add(new GamePiece(hand.get(i).charAt(0), true, -1, -1), i + 1, 0);
		}
	}
	
	
	
}
