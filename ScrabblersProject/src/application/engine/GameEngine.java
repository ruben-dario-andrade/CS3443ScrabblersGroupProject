package application.engine;

import java.util.Collections;
import java.util.LinkedList;

import application.components.GameBoard;
import application.components.GamePlayerTray;
import application.game.Player;

public class GameEngine {
	
	/* Primary game components that belong to GameEngine class */
	public static GameBoard gameBoard;
	private static GamePlayerTray gamePlayerTray;
	
	/* Player object that keeps track of hand and turn actions */
	private static Player player;
	/* These will extend the Player class */
	// Computer computer;
	// Computer computer;
	
	
	private static char[][] boardPiece = new char[15][15];
	private static LinkedList<String> pilePieces;
	private static char[] playerPieces = new char[8];

	
	/* Dynamic variable that tracks the currently selected character */
	private static char currentLetter = ' ';
	/* This variable will keep track of used chars */
	private static LinkedList<String> usedChars;
	private static LinkedList<Point> usedTiles;
	
	public static void start() {
		Player p = new Player();
		WordThread wordThread = new WordThread();
		//System.out.println(wordThread.getX());
		player = p;
		pilePieces = new LinkedList<String>();
		usedChars = new LinkedList<String>();
		for (int i = 0; i < 26; i++) {
			char letter = (char)((int)'A' + i);
			pilePieces.add(letter+"");
		}
		Collections.shuffle(pilePieces);
		for (int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				boardPiece[i][j] = ' ';
			}
		}
		for (int i = 0; i < 7; i++) {
			player.getHand().addPiece(pilePieces.pop());
		}
		gamePlayerTray.addRefreshHand(player.getHand().getList());
	}

	public static void addBoard(GameBoard g) {
		gameBoard = g;
	}
	
	public static void addTray(GamePlayerTray g) {
		gamePlayerTray = g;
	}
	
	

	public static void movePiece(int row, int col) {
		if (currentLetter != ' ') {
			if (boardPiece[row][col] == ' ') {
				gameBoard.addPiece(row, col, currentLetter);
				boardPiece[row][col] = currentLetter;
				usedChars.add(currentLetter+"");
				//usedTiles.add(new Point(row, col));
				player.getHand().removePiece(currentLetter+"");
				gamePlayerTray.addRefreshHand(player.getHand().getList());
			}
		}
		currentLetter = ' ';
	}
	
	public static void receiveLetter(char letter) {
		currentLetter = letter;
	}
	
	public static Player getPlayer() {
		return player;	
	}

	public static void refreshTray() {
		gamePlayerTray.addRefreshHand(player.getHand().getList());
	}
	
	public static void refillHand() {
		usedChars.clear();
		for (int i = 0; i < 7 - player.getHandSize(); i++) {
			if (pilePieces.size() == 0) {
				// End Game function will go here
				break;
			}
			player.getHand().addPiece(pilePieces.pop());
			i--;
		}
		
	}
	
	public static void returnHand() {;
		
		for (int i = 0; i < usedChars.size(); i++) {
			player.getHand().addPiece(usedChars.get(i)+"");
			System.out.println(usedChars.get(i)+"");
		}
		usedChars.clear();
		player.getHand().clearHand();
		gamePlayerTray.addRefreshHand(player.getHand().getList());
	}
	
}













