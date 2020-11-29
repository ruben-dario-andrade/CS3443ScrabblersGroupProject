package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

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
		
		if(SaveModel.currentSave.getSaveNumber() == Integer.parseInt(selectedSaveSlot)) {
			Alert updateCurrentSave = new Alert(AlertType.CONFIRMATION);
			updateCurrentSave.setContentText("Save " + selectedSaveSlot + " has been updated.");
			updateCurrentSave.show();
			
		} else if(!saveFilePath.exists()) {
			String currSaveNumStr = ((Integer)SaveModel.currentSave.getSaveNumber()).toString();
			Alert copyCurrrentSave = new Alert(AlertType.CONFIRMATION);
			copyCurrrentSave.setContentText("Save " + currSaveNumStr + " has been copied to slot " + selectedSaveSlot + ".");
			copyCurrrentSave.show();
			
		} else { // TODO confirmation before user overwrite previous save?
			// Display error to user if save does not exist
			Alert overwriteSave = new Alert(AlertType.CONFIRMATION);
			overwriteSave.setContentText("Save " + selectedSaveSlot + " has been overwritten.");
			overwriteSave.show();
			
		}
		
		// Writes save in selected save slot
		//SaveModel.writeSave(saveFilePath);
		
		// Set current save to null to refresh for future games
		SaveModel.currentSave = null;
		
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
