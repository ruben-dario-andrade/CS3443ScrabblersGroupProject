package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import application.model.GameModel;
import application.model.SaveModel;

import java.io.File;
import java.io.FileOutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SaveGameController implements Initializable{

	@FXML
	private AnchorPane mainPane;
	@FXML
    private Button homeButton;
	@FXML
	private Button slot1;
	@FXML
	private Button slot2;
	@FXML
	private Button slot3;	
	@FXML
	private Button slot4;	
	@FXML
	private Button slot5;
	
	/**
	 *	Save game in slot selected by user
	 * @param event ActionEvent triggered from pushing one of the save buttons
	 */
	@FXML
	public void saveGame(ActionEvent event) throws IOException {
		String selectedSaveSlot = ((Button)event.getSource()).getId();
		String saveFileName = selectedSaveSlot + ".txt";
		File saveFilePath = new File("saves/" + saveFileName);
		
		// Determine user confirmation message based on selected save slot
		Alert userConfirmation = new Alert(AlertType.CONFIRMATION);
		if(SaveModel.currentSave != null) {
			int currSaveNum = SaveModel.currentSave.getSaveNumber();
			String currSaveNumStr = ((Integer)currSaveNum).toString();
			
			if(!saveFilePath.exists()) {
				// Write changed current save to another slot (new save)
				userConfirmation.setContentText("New save " + selectedSaveSlot + " has been written.");
			} else if(saveFilePath.exists() && currSaveNum == Integer.parseInt(selectedSaveSlot)) {
				// Update current save slot
				userConfirmation.setContentText("Save " + selectedSaveSlot + " has been updated.");	
			} else {
				// Overwrite old save with current save
				userConfirmation.setContentText("Save " + selectedSaveSlot + " has been overwritten with save " 
						+ currSaveNum + ".");
			}
			
			// Set current save to null to refresh for future games if played from loaded save
			SaveModel.currentSave = null;
		} else { 
			if(!saveFilePath.exists()) {
				// Write new save in empty slot
				userConfirmation.setContentText("New save " + selectedSaveSlot + " has been written.");
			} else {
				// Overwrite old save with new save
				userConfirmation.setContentText("Save " + selectedSaveSlot + " has been overwritten with new save.");
			}
		}
		
		// Undoes any move that wasn't completed with End Turn before saving
		GameModel.undoMoves();
		
		// Writes save in selected save slot
		SaveModel.writeSave(saveFilePath);
		userConfirmation.show();

		// Switch to home screen after game saved
		mainPane = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
		
	}
	
	/**
	 * Initalizes button IDs
	 */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	//Set button IDs to their save number
        slot1.setId("1");
        slot2.setId("2");
        slot3.setId("3");
        slot4.setId("4");
        slot5.setId("5");
        
	}
}
