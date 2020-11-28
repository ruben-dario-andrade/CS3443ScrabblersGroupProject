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
	
	private SaveModel saveModel;

	@FXML
	public void initialize(){
		
	}
	
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
		File saveFilePath = new File("../../saves/" + saveFileName);
		if(saveFilePath.exists()) {
			// Read save
			SaveModel.readSave(saveFileName);
			
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
		
		System.out.println("Button clicked: " + selectedSave); // TODO
	}
	
	/**
	 * Initalizes button IDs
	 */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	//Set button IDs to their save number
        save1.setId("1");
        save2.setId("2");
        save3.setId("3");
        save4.setId("4");
        save5.setId("5");
	}
}
