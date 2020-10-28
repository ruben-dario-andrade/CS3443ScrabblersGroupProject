package application.controller;

import java.io.IOException;
import java.util.Properties;
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

public class MainController {

	@FXML
	private AnchorPane mainPane;
	
	
	@FXML
	public void initialize(){
		
	}
	
	/*
	@FXML
	public void addLog(ActionEvent event) throws IOException{
		Properties properties = new Properties();
		File file = new File("data.properties");
		FileOutputStream writer = new FileOutputStream(file, true);
		properties.setProperty("spook", "2");
		properties.store(writer, null);
		properties.store(writer, "pressed button");
		System.out.println("spook");
	}
	*/

	
	
}
