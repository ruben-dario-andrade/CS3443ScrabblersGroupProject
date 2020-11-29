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

public class LoadSavesController implements Initializable{

	@FXML
	private AnchorPane mainPane;
	@FXML
    private Button homeButton;
	@FXML
	private Button save1;
	@FXML
	private Button save2;
	@FXML
	private Button save3;	
	@FXML
	private Button save4;	
	@FXML
	private Button save5;
	@FXML
    private Button deleteSave1;
    @FXML
    private Button deleteSave2;
    @FXML
    private Button deleteSave3;
    @FXML
    private Button deleteSave4;
    @FXML
    private Button deleteSave5;
	
	/**
	 * Redirects user to Main screen from LoadSaves screen
	 * @param event ActionEvent triggered from pushing home button
	 * @throws IOException
	 */
	@FXML
	public void goHome(ActionEvent event) throws IOException{
		mainPane = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	/**
	 * Loads save selected by user
	 * @param event ActionEvent triggered from pushing one of the save buttons
	 */
	@FXML
	public void loadSave(ActionEvent event) throws IOException {
		String selectedSave = ((Button)event.getSource()).getId();
		String saveFileName = selectedSave + ".txt";
		File saveFilePath = new File("saves/" + saveFileName);
		if(saveFilePath.exists()) {
			// Read save
			SaveModel.readSave(saveFilePath);
			
			// Switch to game screen with save loaded
			mainPane = FXMLLoader.load(getClass().getResource("../view/GameScreen.fxml"));
			Scene scene = new Scene(mainPane);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
			
		} else {
			// Display error to user if save does not exist
			Alert noSave = new Alert(AlertType.ERROR);
			noSave.setContentText("Save does not exist");
			noSave.show();
		}
	}
	
	/**
	 * Delete save if it exists
	 * @param event ActionEvent triggered by pressing delete save button
	 */
	public void deleteSave(ActionEvent event) {
		String selectedSave = ((Button)event.getSource()).getId();
		String saveFileName = selectedSave + ".txt";
		File saveFilePath = new File("saves/" + saveFileName);
		
		if(saveFilePath.delete()) {
			// Display error to user if save does not exist
			Alert deleteSuccess = new Alert(AlertType.CONFIRMATION);
			deleteSuccess.setContentText("Save " + selectedSave + " has been deleted.");
			deleteSuccess.show();
		} else {
			// Display error to user if save does not exist
			Alert noSave = new Alert(AlertType.ERROR);
			noSave.setContentText("Save does not exist");
			noSave.show();
		}
	}
	
	/**
	 * Initalizes button IDs
	 */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	// Set save button IDs to their save number
        save1.setId("1");
        save2.setId("2");
        save3.setId("3");
        save4.setId("4");
        save5.setId("5");
        
        // Set delete buttons to their associated save number
        deleteSave1.setId("1");
        deleteSave2.setId("2");
        deleteSave3.setId("3");
        deleteSave4.setId("4");
        deleteSave5.setId("5");
	}
}
