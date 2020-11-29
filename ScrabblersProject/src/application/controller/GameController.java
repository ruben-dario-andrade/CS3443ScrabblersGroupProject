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
import application.model.HelperModel;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameController implements Initializable {
	
	@FXML
	private AnchorPane mainPane;
	
	//@FXML
    //private AnchorPane WordHelperPane;

	@FXML
	private BorderPane WordHelperPane;
    //@FXML
    //private Label WFLabel;

    //@FXML
    //private ListView<String> WordDisplayLV;
    
    @FXML
    private Button EndTurnButton;
    
    @FXML 
    private Button UndoButton;
   
    @FXML
    private Button ExitButton;
    
    @FXML 
    private AnchorPane GameBoardPane;
    
    @FXML
    private Label AlertLabel;

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
		
		GameEngine.start(gameBoard, gamePlayerTray);
	}
	
	@FXML
	public void endTurn(ActionEvent event) {
    boolean correctWord = false;
		
		if(GameModel.endTurn()) {
			correctWord = true;
		}

		if(correctWord == true) {
			AlertLabel.setText("Valid word! Your/Opponent's move.");
		}
		else {
			AlertLabel.setText("Invalid word. Please try again.");
		}
	}
	
	@FXML
	public void undoMoves(ActionEvent event) {
		GameModel.undoMoves();
	}
	
	@FXML
	public void goHome(ActionEvent event) throws IOException{
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
		HelperModel.close();
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