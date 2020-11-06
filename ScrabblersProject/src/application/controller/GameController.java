package application.controller;

import java.io.IOException;
import java.util.Properties;

import application.components.GameBoard;
import application.components.GamePiece;
import application.components.GamePlayerTray;
import application.engine.GameEngine;

import java.io.File;
import java.io.FileOutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameController {
	
	@FXML
	private AnchorPane mainPane;
	
	@FXML
	public void initialize(){
		GameBoard gameBoard = new GameBoard();
		GamePlayerTray gamePlayerTray = new GamePlayerTray();
		gamePlayerTray.setLayoutX(50);
		gamePlayerTray.setLayoutY(650);
		mainPane.getChildren().add(gameBoard);
		mainPane.getChildren().add(gamePlayerTray);
		GameEngine.addBoard(gameBoard);
		GameEngine.addTray(gamePlayerTray);
		GameEngine.start();
	}
	
	
	
	
}
















