package application.engine;

import java.util.LinkedList;

import application.components.GameBoard;
import application.components.GamePiece;

public class GameEngine {
	
	class GameEnginePiece{
		char letter;
		int row;
		int column;
		GameEnginePiece(char letter, int row, int column){
			this.letter = letter;
			this.row = row;
			this.column = column;
		}
	}
	
	
	public static GameBoard gameBoard;
	
	public static char[][] boardPiece = new char[15][15];
	public static char[] pilePieces = new char[101];
	public static char[] playerPieces = new char[8];

	public static void addBoard(GameBoard g) {
		gameBoard = g;
	}
	
	public static void start() {
		gameBoard.getGameTile(4, 4).getChildren().add(new GamePiece("F"));
		GamePiece hold = (GamePiece)gameBoard.getGameTile(4, 4).getChildren().get(0);
		System.out.println(hold.getLetter());
	}

	public static void spook() {
		System.out.println("spook");
	}

}
