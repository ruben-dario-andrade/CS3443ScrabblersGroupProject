package application.controller;

import java.io.IOException;
import java.util.Properties;

import application.components.GameBoard;
import application.components.GamePiece;
import application.components.GameTile;
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
		mainPane.getChildren().add(gameBoard);
		GameEngine.addBoard(gameBoard);
		GameEngine.start();
		//gameBoard.getGameTile(4, 4).getChildren().add(new GamePiece("F"));
	}
	
	
}
