package application.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;

import application.algo.WordThread;
import application.engine.LoadFxml;
import application.model.HelperModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class WordHelperController implements Initializable {
	
	@FXML
	private BorderPane WordHelperPane;
	
	@FXML
    private ListView<String> WordDisplayLV;
	
	WordThread wordThread;
	
    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<String> list2 = FXCollections.observableArrayList();


    LinkedList<String> LL = new LinkedList<String>();
    LinkedList<String> LL2 = new LinkedList<String>();

    /*
    public void populateLLThread(LinkedList<String> rec) {
    	//System.out.println("I call this");
    	//LinkedList<String> LL3 = new LinkedList<String>();
		//LL3.add("Spooker");
    	//LL3.add("Spooper");
    	//LL3.add("Speeker");
    	//list.setAll(LL3);
    	//WordDisplayLV.setItems(list);
    	list.setAll(rec);
		WordDisplayLV.setItems(list);
    }
	*/
    
	@FXML
	public void populateLL(ActionEvent event) {
		LinkedList<String> LL3 = wordThread.getReccomendations();
    	list.setAll(LL3);
		WordDisplayLV.setItems(list);
	}
	
	@FXML
	public void populateLL2(ActionEvent event) {
		LinkedList<String> LL3 = new LinkedList<String>();
		LL3.add("Bow");
    	LL3.add("Low");
    	LL3.add("Crow");
    	list.setAll(LL3);
		WordDisplayLV.setItems(list);
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.wordThread = new WordThread();
		HelperModel.start(wordThread);
		//wordThread.end();
	}

	
	
	
}






















