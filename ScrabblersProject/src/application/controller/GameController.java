package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import application.components.GameBoard;
import application.components.GamePiece;
import application.components.GamePlayerTray;
import application.engine.GameEngine;
import application.engine.LoadFxml;
import application.model.GameModel;
import application.model.SaveModel;

import java.io.File;
import java.io.FileOutputStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameController implements Initializable {
	
	@FXML
	private AnchorPane mainPane;

	@FXML
	private BorderPane WordHelperPane;
	
	@FXML
    private TextField scoreTF;
    
    @FXML
    private Button EndTurnButton;
    
    @FXML 
    private Button UndoButton;
   
    @FXML
    private Button RefreshButton;
    
    @FXML
    private Button ExitButton;
    
    @FXML 
    private AnchorPane GameBoardPane;
    
    @FXML
    private Label AlertLabel;

    private int score = 0;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		PopupControl popup = new PopupControl();

		// if playing saved game, initialize GUI game board with saved pieces
		GameBoard gameBoard;
		if(SaveModel.currentSave != null) {
			gameBoard = new GameBoard(SaveModel.currentSave.getSavedBoardPieces());
		} else {
			gameBoard = new GameBoard();
		}
		
		GamePlayerTray gamePlayerTray = new GamePlayerTray(); // TODO
		gamePlayerTray.setLayoutX(50);
		gamePlayerTray.setLayoutY(650);
		GameBoardPane.getChildren().add(gameBoard);
		mainPane.getChildren().add(gamePlayerTray);
		
		// Initialize game components with save data if it exists
		if(SaveModel.currentSave != null) {
			char[][] savedBoardPieces = SaveModel.currentSave.getSavedBoardPieces();
			LinkedList<String> savedPlayerTray = SaveModel.currentSave.getSavedPlayerTray();
			LinkedList<String> savedGamePile = SaveModel.currentSave.getSavedGamePile();
			GameEngine.start(gameBoard, gamePlayerTray, savedBoardPieces, savedPlayerTray, savedGamePile);
		} else {
			GameEngine.start(gameBoard, gamePlayerTray);
		}
		
		// Player's score should be added and 
		scoreTF.setText(String.valueOf(0));
		
	}
	
	@FXML
	public void endTurn(ActionEvent event) {
		String text = GameModel.endTurn();	
		this.score += GameModel.score;
		// Player's score should be a running total
		if (text == "Valid word! Your move.") {
			scoreTF.setText(String.valueOf(score));
		}
		AlertLabel.setText(text);
	}
	
	@FXML
	public void undoMoves(ActionEvent event) {
		GameModel.undoMoves();
	}
	
	@FXML
	public void refreshLetters(ActionEvent event) {
		String text = GameModel.refreshLetters();
		AlertLabel.setText(text);
		//GameModel.refreshLetters();
	}
	
	@FXML
	public void goHome(ActionEvent event) throws IOException{
		// Undoes any move that wasn't completed with End Turn before saving
		GameModel.undoMoves();
		
		Alert userConfirmation = new Alert(AlertType.CONFIRMATION);
		if(SaveModel.currentSave != null) {
			int currSaveNum = SaveModel.currentSave.getSaveNumber();
			
			// Update save in current slot
			File updateSaveFilePath = new File("saves/" + currSaveNum + ".txt");
			SaveModel.writeSave(updateSaveFilePath);
			
			// Clear current save to refresh for next game
			SaveModel.currentSave = null;

			userConfirmation.setContentText("Save " + currSaveNum + " has been updated.");	
		} else {
			File saveDir = new File("saves");
			int openSlot = SaveModel.checkOpenSlot(saveDir);
			
			if(openSlot < 0) {
				// Default overwrite save in slot 1 TODO better default behavior may be overwrite oldest save (how?)
				File defaultSaveFilePath = new File("saves/1.txt");
				SaveModel.writeSave(defaultSaveFilePath);
				
				userConfirmation.setContentText("Save 1 has been overwritten with new save.");
			} else {
				// Write save in open slot
				File openSaveFilePath = new File("saves/" + openSlot + ".txt");
				SaveModel.writeSave(openSaveFilePath);
				
				userConfirmation.setContentText("New save " + openSlot + " has been written.");
			}
		}
		userConfirmation.show();
		
		// Switch to main screen after writing save
		mainPane = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@FXML
	public void OpenWordHelper(ActionEvent event) {
		LoadFxml object = new LoadFxml();
		Pane view = object.getPage("DisplayWords");
		WordHelperPane.setCenter(view);
	}
	
	@FXML
	public void CloseWordHelper(ActionEvent event) {
		//HelperModel.close();
		LoadFxml object = new LoadFxml();
		Pane view = object.getPage("Blank");
		WordHelperPane.setCenter(view);
	}
	
	@FXML
	public void saveGame(ActionEvent event) {
		LoadFxml object = new LoadFxml();
		Pane view = object.getPage("SaveGame");
		WordHelperPane.setCenter(view);
	}
	
}