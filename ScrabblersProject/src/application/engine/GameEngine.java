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
	private static boolean isReplacing;
	private static LinkedList<String> usedChars;
	private static LinkedList<Point> usedTiles = new LinkedList<Point>();
	
	
	public static void start(GameBoard gameBoard, GamePlayerTray gamePlayerTray) {
		engineBoard = new EngineBoard(gameBoard);
		engineTray = new EngineTray(gamePlayerTray);
		enginePile = new EnginePile();
		usedChars = new LinkedList<String>();
		isReplacing = false;
		
		// Adds 7 letters from pile to user hand to start off with
		for (int i = 0; i < 7; i++) {
			engineTray.addPiece(enginePile.popLetter());
		}
		gamePlayerTray.addHand(engineTray.getList());
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
		isReplacing = false;
		gamePlayerTray.addHand(engineTray.getList());
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
			 engineTray.resetTray();
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
		if (isReplacing) {
			currentLetter = ' ';
			engineTray.removePiece(letter+"");
			engineTray.addRefreshPiece(letter+"");
			engineTray.resetTray();
		} else {
			currentLetter = letter;
		}
	}
	
	public static void commitRefresh() {
		enginePile.commitList(engineTray.getRefreshList());
		engineTray.clearRefreshList();
	}
	
	public static void undoRefresh() {
		engineTray.returnRefreshPieces();
		engineTray.clearHand();
		engineTray.resetTray();
	}
	
	public static void resetTray() {
		engineTray.resetTray();
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
	
	/*
	 * Description:
	 * 		This function returns hand to original state of turn.
	 */
	public static void returnHand() {
		engineTray.clearHand();
		for (int i = 0; i < usedChars.size(); i++) {
			engineTray.addPiece(usedChars.get(i)+"");
		}
		usedChars.clear();
		engineTray.resetTray();
	}
	
	/*
	 * Description:
	 * 		This function returns board to original state of turn.
	 */
	public static void returnBoard() {
		engineBoard.returnBoard(usedTiles);
		usedTiles.clear();
	}
	
	/*
	 * Description:
	 * 		Clears the used letters and tiles tracked by the class
	 */
	public static void clearUsedPieces() {
		usedChars.clear();
		usedTiles.clear();
	}
	
	/*
	 * Return:
	 * 		A LinkedList of letters excluding blank chars. 
	 */
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

	/*
	 * Return:
	 * 		 char tracked by the class
	 */
	public static char getCurrentLetter() {
		return currentLetter;
	}
	
	
	/*
	 * Return:
	 * 		 boolean that denotes if the entered moves are valid
	 */
	public static int checkValid() {
		LinkedList<String> testStrings = new LinkedList<String>();
 		testStrings = WordVerification.validWord(engineBoard.getBoard(), usedChars, usedTiles);
 		if (testStrings == null) {
 			return 0;
 		} else {
 			LinkedList<String> holdStrings = new LinkedList<String>();
 			holdStrings = WordVerification.searchRemaining(engineBoard.getBoard(), usedChars, usedTiles);
 			if (holdStrings.size() > 0) {
 				testStrings.addAll(holdStrings);
 			}
 		}
 		if (!WordVerification.inDictionary(testStrings)) {
 			return 0;
 		}
 		//System.out.println("I get here");
 		//WordPointCount.countPoints(usedTiles, testStrings, engineBoard.getBoard());
 		return 1;
	}
	
	/*
	 * Return:
	 * 		 char[][] holding the current char values of the board
	 */
	public static char[][] getBoard(){
		return engineBoard.getBoard();
	}
	
	/*
	 * Return:
	 *		 boolean state denoting if GameEngine is in replacing mode 	
	 */
	public static boolean getIsReplacing() {
		return isReplacing;
	}
	
	/*
	 * Return:
	 *		 String stating instruction dependent on state of GameEngine 
	 * Description:
	 * 		 Sets replacing boolean state, no board moves bust have been made to set to true 
	 */
	public static String setIsReplacing(boolean replacing) {
		isReplacing = replacing;
		if (usedTiles.size() != 0) {
			isReplacing = false;
			return "Please end turn or undo moves before replacing letters";
		}
		if (replacing == true) {
			return "Select letters you wish to replace.";
		}
		return "";
	}
	
	/*
	 * Return:
	 *		 engineTray	
	 */
	public static EngineTray getTray() {
		return engineTray;	
	}
	
	/*
	 * Return:
	 *		 LinkedList of current pile pieces
	 */
	public static LinkedList<String> getPile(){
		return enginePile.getPile();
	}
  
}
