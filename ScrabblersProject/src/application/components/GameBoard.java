package application.components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameBoard extends GridPane{
	public GameBoard() {
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				GamePiece gamePiece = new GamePiece(' ', false, i, j);
				this.add(gamePiece, i, j);
			}
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/ScrabbleBoard.txt"));
			String line = reader.readLine();
			int row, col, mult;
			while (line != null) {
				String[] specialTile = line.split(" ", 3);
				row = Integer.parseInt(specialTile[0]);
				col = Integer.parseInt(specialTile[1]);
				mult = Integer.parseInt(specialTile[2]);
				GamePiece gamePiece = new GamePiece(' ', false, row, col);
				if (mult == 3) {
					gamePiece.setStyle("-fx-background-color: mediumaquamarine");
				} else if (mult == 2) {
					gamePiece.setStyle("-fx-background-color: lightsteelblue");
				}
				removeNodeByRowColumnIndex(row,col);
				this.add(gamePiece, row, col);
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("file not found");
		}
	}
	
	public GamePiece getGameTile(final int rows, final int columns) {
		return (GamePiece)this.getChildren().get(rows*15 + columns);
	}

	public void addPiece(int row, int col, char letter, int mult) {
		GamePiece gamePiece = new GamePiece(letter, false, row, col);
		if (mult == 3) {
			gamePiece.setStyle("-fx-background-color: mediumaquamarine");
		} else if (mult == 2) {
			gamePiece.setStyle("-fx-background-color: lightsteelblue");
		}
		this.add(gamePiece, row, col);
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
