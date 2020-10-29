package application.components;

import application.engine.GameEngine;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Font;

public class GamePiece extends Label{
	
	private String letter;
	
	public GamePiece(String letter) {
		
		this.letter = letter;
		
		Border border = new Border(new BorderStroke(null,
				BorderStrokeStyle.SOLID,
				new CornerRadii(0),
				new BorderWidths(1)
		));
		
		this.setBorder(border);
		this.setMaxSize(36, 36);
		this.setPrefSize(36, 36);
		this.setMinSize(36, 36);
		this.setPadding(new Insets(1, 1, 0 ,0));
		this.setText(letter);
		this.setFont(new Font("Verdana", 25));
		
		this.addClick();
		
	}
	
	public void addClick() {
		EventHandler<MouseEvent> clickDetected = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				GameEngine.spook();
			}
		};
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, clickDetected);
	}
	
	public String getLetter() {
		return letter;
	}
	
	
}
