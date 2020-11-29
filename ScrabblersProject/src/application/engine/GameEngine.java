package application.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;

import application.algo.WordPointCount;
import application.algo.WordVerification;
import application.components.GameBoard;
import application.components.GamePlayerTray;

public class GameEngine {
	
	private static EngineBoard engineBoard;
	private static EnginePile enginePile;
	private static EngineTray engineTray;

	
	private static char currentLetter = ' ';
	private static LinkedList<String> usedChars;
	private static LinkedList<Point> usedTiles = new LinkedList<Point>();
	
	
	public static void start(GameBoard gameBoard, GamePlayerTray gamePlayerTray) {
		engineBoard = new EngineBoard(gameBoard);
		engineTray = new EngineTray(gamePlayerTray);
		enginePile = new EnginePile();
		usedChars = new LinkedList<String>();
		
		// Adds 7 letters from pile to user hand to start off with
		for (int i = 0; i < 7; i++) {
			engineTray.addPiece(enginePile.popLetter());
		}
		gamePlayerTray.addRefreshHand(engineTray.getList());
	}
	
	/**
	 * Start game with game board, player tray, and game pile initialized with saved pieces
	 * @param gameBoard GUI game board
	 * @param gamePlayerTray GUI player tray
	 * @param savedBoard 2D char array holding saved board pieces
	 * @param savedTray LinkedList holding saved tray pieces
	 * @param savedPile LinkedList holding saved tray pieces
	 */
	public static void start(GameBoard gameBoard, GamePlayerTray gamePlayerTray, 
			char[][] savedBoard, LinkedList<String> savedTray, LinkedList<String> savedPile) {
		engineBoard = new EngineBoard(gameBoard, savedBoard);
		engineTray = new EngineTray(gamePlayerTray, savedTray);
		enginePile = new EnginePile(savedPile);
		usedChars = new LinkedList<String>();

		gamePlayerTray.addRefreshHand(engineTray.getList());
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
		 if (engineBoard.movePiece(row, col) ) {
			 usedChars.add(currentLetter+"");
			 Point temp = new Point(row, col);
			 usedTiles.add(temp);
			 engineTray.removePiece(currentLetter+"");
			 engineTray.refreshTray();
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
	
	public static EngineTray getTray() {
		return engineTray;	
	}

	public static void refreshTray() {
		engineTray.refreshTray();
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
		for (int i = 0; i < 7 - engineTray.getHandSize(); i++) {
			if (enginePile.getSize() <= 0) {
				return -1;
			}
			engineTray.addPiece(enginePile.popLetter());
			i--;
		}
		return 0;
	}
	
	public static void returnHand() {
		engineTray.clearHand();
		for (int i = 0; i < usedChars.size(); i++) {
			engineTray.addPiece(usedChars.get(i)+"");
		}
		usedChars.clear();
		engineTray.refreshTray();
	}
	
	public static void returnBoard() {
		engineBoard.returnBoard(usedTiles);
		usedTiles.clear();
	}
	
	public static void clearUsedPieces() {
		usedChars.clear();
		usedTiles.clear();
	}
	
	public static LinkedList<String> getHand(){
		LinkedList<String> adj = new LinkedList<String>();
		int size = engineTray.getList().size();
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (engineTray.getList().get(i).charAt(0) != ' ') {
				adj.add(engineTray.getList().get(i));
				count++;
			}
		}
		return adj;
	}

	public static char getCurrentLetter() {
		return currentLetter;
	}
	
	public static boolean checkValid() {
		LinkedList<String> testStrings = new LinkedList<String>();
 		testStrings = WordVerification.validWord(engineBoard.getBoard(), usedChars, usedTiles);
 		if (testStrings == null) {
 			return false;
 		} else {
 			LinkedList<String> holdStrings = new LinkedList<String>();
 			holdStrings = WordVerification.searchRemaining(engineBoard.getBoard(), usedChars, usedTiles);
 			if (holdStrings.size() > 0) {
 				testStrings.addAll(holdStrings);
 			}
 		}
 		if (!WordVerification.inDictionary(testStrings)) {
 			return false;
 		}
 		//Print this -> WordPointCount.countPoints(usedTiles, testStrings, engineBoard.getBoard());
 		return true;
	}
	
	public static char[][] getBoard(){
		return engineBoard.getBoard();
	}
}
