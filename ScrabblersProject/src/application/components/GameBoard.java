package application.components;

import javafx.scene.layout.GridPane;

public class GameBoard extends GridPane{
	public GameBoard() {
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				this.add(new GameTile(), i, j);
			}
		}
	}
	
	public GameTile getGameTile(final int rows, final int columns) {
		return (GameTile)this.getChildren().get(rows*15 + columns);
	}
	
}
