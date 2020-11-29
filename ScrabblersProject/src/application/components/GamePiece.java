package application.components;

import application.engine.GameEngine;
import application.event.PieceEvent;
import application.event.PieceEventBoard;
import application.event.PieceEventHandler;
import application.event.PieceEventTray;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Font;

/* This class extends the button piece, and is what comprises both tiles and game pieces.
 * 
 */

public class GamePiece extends Button{
	
	private char letter;
	boolean inTray;
	int row = -1;
	int col = -1;
	
	public GamePiece(char letter, boolean inTray, int row, int col) {
		
		this.letter = letter;
		this.inTray = inTray;
		this.row = row;
		this.col = col;
		
		
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
		this.setText(Character.toString(letter));
		this.setFont(new Font("Verdana", 25));
		this.setStyle("-fx-background-color: mintcream");
		
		
		/* Fire custom event handler */
		this.setOnAction((ActionEvent event) ->  {
			this.fireEvent(new PieceEventTray(letter, inTray));
			this.fireEvent(new PieceEventBoard(row, col, inTray));
		});
		
		this.addEventHandler(PieceEvent.CUSTOM_EVENT_TYPE, new PieceEventHandler() {
			
			@Override
			public void onEvent1(char letter, boolean inTray) {
				if (inTray) {
					GameEngine.receiveLetter(letter);
				}
			}
			
			@Override
			public void onEvent2(int row, int col, boolean inTray) {
				if (!inTray) {
					GameEngine.movePiece(row, col);
				}
			}
		});
		
	}


	
	
}

























