package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

import application.components.GameBoard;
import application.components.GamePiece;
import application.components.GamePlayerTray;
import application.engine.GameEngine;
import application.model.GameModel;

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
import javafx.stage.Stage;

public class GameController implements Initializable {
	
	@FXML
	private AnchorPane mainPane;
	
	@FXML
    private AnchorPane WordHelperPane;

    @FXML
    private Label WFLabel;

    @FXML
    private ListView<String> WordDisplayLV;
    
    @FXML
    private Button EndTurnButton;
    
    @FXML 
    private Button UndoButton;
    
    @FXML
    private Button ExitButton;
    
    ObservableList<String> list = FXCollections.observableArrayList("This", "is", "to", "populate", "this", "listview");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		PopupControl popup = new PopupControl();
		
		
		GameBoard gameBoard = new GameBoard();
		GamePlayerTray gamePlayerTray = new GamePlayerTray();
		gamePlayerTray.setLayoutX(50);
		gamePlayerTray.setLayoutY(650);
		mainPane.getChildren().add(gameBoard);
		mainPane.getChildren().add(gamePlayerTray);
		GameEngine.addBoard(gameBoard);
		GameEngine.addTray(gamePlayerTray);
		GameEngine.start();
		// Code below is commented out for future use (will be used to read from text file)
		/*try {
			Scanner scan = new Scanner(new File("sample.txt"));
			while(scan.hasNext()) {
				list.add(scan.next());
			}
		}
		catch (FileNotFoundException e) {
			System.out.println(e);
		}*/
		
		WordDisplayLV.setItems(list);
	}
	
	@FXML
	public void endTurn(ActionEvent event) {
		GameModel.endTurn();
		
	}
	
	@FXML
	public void undoMoves(ActionEvent event) {
		GameModel.undoMoves();
	}
	
}
















