package application.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import application.components.GameSave;
import application.engine.GameEngine;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SaveModel {
	
	// static so other files may refer to the current save if it exists
	public static GameSave currentSave;
	
	/**
	 * Read saved game data from txt file into a GameSave object
	 * @param saveFile
	 */
	public static void readSave(File saveFile) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(saveFile));
			
			// Parse save number from save file name
			int saveNumber = Integer.parseInt(saveFile.getName().split("\\.")[0]);
			
			// Read and store saved player tray and game pile
			String savedTrayLine = reader.readLine();
			String[] savedTrayTemp = savedTrayLine.split(",");
			LinkedList<String> savedTray = new LinkedList<String>(Arrays.asList(savedTrayTemp));
			
			String savedPileLine = reader.readLine();
			String [] savedPileTemp = savedPileLine.split(",");
			LinkedList<String> savedPile = new LinkedList<String>(Arrays.asList(savedPileTemp));
			
			// Read and store saved board pieces
			char[][] savedBoardPieces = new char[15][15];
			for(int i = 0; i < 15; i++) {
				String[] temp = reader.readLine().split(",");
				for(int j = 0; j < 15; j++) {
					savedBoardPieces[i][j] = temp[j].charAt(0);
				}
			}
			
			reader.close();
			
			// Create save obj and set global currentSave to it for reference from other controllers
			GameSave loadedSave = new GameSave(saveNumber, savedBoardPieces, savedTray, savedPile);
			currentSave = loadedSave;
		} catch(IOException e) {
			e.printStackTrace();
			
			// Display error to user
			Alert loadSaveFail = new Alert(AlertType.ERROR);
			loadSaveFail.setContentText("Save could not be loaded.");
			loadSaveFail.show();
		}
	}
	
	/**
	 * Writes save file to saves folder
	 * @param saveFile file path to write save file to
	 */
	public static void writeSave(File saveFile) {
		// Get current game components to save state
		char[][] boardPieces = GameEngine.getBoard();
		LinkedList<String> playerTray = GameEngine.getHand();
		LinkedList<String> gamePile = GameEngine.getPile();
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
			
			// Write player tray to save file
			for(String trayPiece : playerTray) {
				writer.write(trayPiece + ",");
			}
			writer.newLine();
			
			// Write game pile to save file
			for(String pilePiece : gamePile) {
				writer.write(pilePiece + ",");
			}
			writer.newLine();
			
			// Write board pieces to save file
			for(int i = 0; i < 15; i++) {
				for(int j = 0; j < 15; j++) {
					writer.write(boardPieces[i][j] + ",");
				}
				writer.newLine();
			}
			
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
			
			// Display error to user that save couldn't be written
			Alert writeSaveFail = new Alert(AlertType.ERROR);
			writeSaveFail.setContentText("Save could not be written.");
			writeSaveFail.show();
		}
	}
	
	/**
	 * Check next available save slot during attempt to autosave new game
	 * @return -1 for no available slots or the available slot number
	 */
	public static int checkOpenSlot(File saveDir) {
		boolean[] usedSlots = new boolean[5];
		
		// Check which save slots are used
		for(File saveFile: saveDir.listFiles()) {
			int usedSlot = Integer.parseInt(saveFile.getName().split("\\.")[0]) - 1;
			usedSlots[usedSlot] = true;
		}
		
		// Return first open slot
		for(int i = 0; i < usedSlots.length; i++) {
			if(!usedSlots[i]) { return i + 1; }
		}
		
		return -1;
	}
}
