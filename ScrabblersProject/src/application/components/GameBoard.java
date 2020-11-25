package application.components;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameBoard extends GridPane{
	public GameBoard() {
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				this.add(new GamePiece(' ', false, i, j), i, j);
			}
		}
		
		
	}
	
	public GamePiece getGameTile(final int rows, final int columns) {
		return (GamePiece)this.getChildren().get(rows*15 + columns);
	}

	public void addPiece(int row, int col, char letter) {
		this.add(new GamePiece(letter, false, row, col), row, col);
	}
	
	public void removeNodeByRowColumnIndex(final int row,final int column) {

		ObservableList<Node> childrens = this.getChildren();
		for(Node node : childrens) {
		    if(node instanceof ImageView && this.getRowIndex(node) == row && this.getColumnIndex(node) == column) {
		        //GamePiece gamePiece = GamePiece(node); // use what you want to remove
		        this.getChildren().remove(node);
		        break;
		    }
		  } 
	}
	
}
