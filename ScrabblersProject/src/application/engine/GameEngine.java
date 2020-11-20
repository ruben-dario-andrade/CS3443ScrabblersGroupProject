package application.engine;

import java.io.BufferedReader;
import java.io.FileReader;
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
		
		player = p;
		pilePieces = new LinkedList<String>();
		pilePieces = retrievePilePieces();
		usedChars = new LinkedList<String>();
		
		
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
		WordThread wordThread = new WordThread();
	}

	public static void addBoard(GameBoard g) {
		gameBoard = g;
	}
	
	public static void addTray(GamePlayerTray g) {
		gamePlayerTray = g;
	}
	
	
	/* Parameters: 	
	 * 		int row, col -> row and column received from the piece event
	 * Return:
	 * 		None:
	 * Description:
	 * 		This function sets the new GamePiece onto the board and removes the piece from
	 * 		the players hand. 
	 */
	public static void movePiece(int row, int col) {
		if (currentLetter != ' ') {
			if (boardPiece[row][col] == ' ') {
				/* This pair of statements sets the letters both on the GameBoard object
				 * and the locally held game board char[][] array*/
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
	
	/* Parameters: 	
	 * 		char letter -> letter received from the piece event
	 * Return:
	 * 		None:
	 * Description:
	 * 		This function sets the current letter held by the GameEngine class
	 * 		This current letter is used to set the letter on the grid the user selects
	 */
	public static void receiveLetter(char letter) {
		currentLetter = letter;
	}
	
	public static Player getPlayer() {
		return player;	
	}

	public static void refreshTray() {
		gamePlayerTray.addRefreshHand(player.getHand().getList());
	}
	
	/* Parameters: 	
	 * 		None:
	 * Return:
	 * 		int 0 if the refill was success
	 * 		int -1 if the refill fails
	 * Description:
	 * 		This function refills the player's hand from the remaining pieces
	 */
	public static int refillHand() {
		usedChars.clear();
		for (int i = 0; i < 7 - player.getHandSize(); i++) {
			if (pilePieces.size() == 0) {
				return -1;
			}
			player.getHand().addPiece(pilePieces.pop());
			i--;
		}
		return 0;
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
	
	public static LinkedList<String> getHand(){
		return player.getHand().getList();
	}
	
	public static LinkedList<String> retrievePilePieces(){
		LinkedList<String> pilePieces = new LinkedList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/ScrabblePile.txt"));
			String line = reader.readLine();
			while (line != null) {
				for (int i = 0; i < line.length(); i++) {
					pilePieces.add(line.charAt(i)+"");
				}
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception e) {
			System.out.println("file not found");
		}
		return pilePieces;
	}
	
}













