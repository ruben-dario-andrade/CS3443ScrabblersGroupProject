package application.components;

import java.util.LinkedList;

public class GameSave {
	
	private int saveNumber;
	private char[][] boardPieces;
	private LinkedList<String> playerTray;
	private LinkedList<String> gamePile;
	
	/**
	 * Initialize GameSave object with saved data read from save file
	 * @param savedPieces 2D array holding saved board pieces
	 * @param savedTray LinkedList holding saved player tray
	 * @param savedPile LinkedList holding saved game pile
	 */
	public GameSave(int saveNumber, char[][] savedPieces, LinkedList<String> savedTray, LinkedList<String> savedPile) {
		this.saveNumber = saveNumber;
		this.boardPieces = savedPieces;
		this.playerTray = savedTray;
		this.gamePile = savedPile;
	}
	
	/**
	 * Gets save number
	 * @return save number
	 */
	public int getSaveNumber() {
		return this.saveNumber;
	}

	/**
	 * Gets board pieces from save
	 * @return 2D array holding saved board pieces
	 */
	public char[][] getSavedBoardPieces() {
		return this.boardPieces;
	}
	
	/**
	 * Gets player tray from save
	 * @return LinkedList holding saved player tray
	 */
	public LinkedList<String> getSavedPlayerTray() {
		return this.playerTray;
	}
	
	/**
	 * Gets game pile from save
	 * @return LinkedList holding saved game pile
	 */
	public LinkedList<String> getSavedGamePile() {
		return this.gamePile;
	}
	
	/**
	 * Update saved board pieces with updated board pieces
	 * @param newBoardPieces updated board pieces to be stored in save
	 */
	public void updateSavedBoardPieces(char[][] newBoardPieces) {
		this.boardPieces = newBoardPieces;
	}
	
	/**
	 * Update saved player tray with updated player tray
	 * @param newBoardPieces updated player tray to be stored in save
	 */
	public void updateSavedPlayerTray(LinkedList<String> newPlayerTray) {
		this.playerTray = newPlayerTray;
	}
	
	/**
	 * Update saved game pile with updated game pile
	 * @param newBoardPieces updated game pile to be stored in save
	 */
	public void updateSavedGamePile(LinkedList<String> newGamePile) {
		this.gamePile = newGamePile;
	}
}
