package application.engine;

import java.util.LinkedList;

import application.components.GameBoard;
import application.components.GamePlayerTray;

public class GameEngine {
		
	public static GameBoard gameBoard;
	public static GamePlayerTray gamePlayerTray;
	
	public static char[][] boardPiece = new char[15][15];
	public static LinkedList<String> pilePieces;
	public static char[] playerPieces = new char[8];

	
	private static char currentLetter = ' ';
	
	
	
	public static void start() {
		pilePieces = new LinkedList<String>();
		
		for (int i = 0; i < 26; i++) {
			char letter = (char)((int)'A' + i);
			pilePieces.add(letter+"");
		}
		
		for (int i = 0; i < 8; i++) {
			gamePlayerTray.addPiece(pilePieces.pop().charAt(0));
		}
		
	}

	public static void addBoard(GameBoard g) {
		gameBoard = g;
	}
	
	public static void addTray(GamePlayerTray g) {
		gamePlayerTray = g;
	}
	
	

	public static void movePiece(int row, int col) {
		if (currentLetter != ' ') {
			if (gameBoard.getGameTile(row, col).getLetter() == ' ') {
				gameBoard.addPiece(row, col, currentLetter);
				boardPiece[row][col] = currentLetter;
				gamePlayerTray.removePiece(currentLetter);;
			}
		}
		currentLetter = ' ';
	}
	
	public static void receiveLetter(char letter) {
		currentLetter = letter;
	}

}













