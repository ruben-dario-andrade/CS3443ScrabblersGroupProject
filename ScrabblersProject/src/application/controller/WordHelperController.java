package application.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;

import application.engine.LoadFxml;
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
	
    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<String> list2 = FXCollections.observableArrayList();


    LinkedList<String> LL = new LinkedList<String>();
    LinkedList<String> LL2 = new LinkedList<String>();

    
    /*public void populateLL(LinkedList<String> LL) {
    	LL.add("A");
    	LL.add("B");
    	
    	list.setAll(LL);
    }*/
	
	@FXML
	public void populateLL(ActionEvent event) {
		WordDisplayLV.setItems(list);
	}
	
	@FXML
	public void populateLL2(ActionEvent event) {
		WordDisplayLV.setItems(list2);
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Code below is commented out for future use (will be used to read from text file)
				/*try {
					Scanner scan = new Scanner(new File("res/test2.txt"));
					while(scan.hasNext()) {
						list.add(scan.next());
					}
				}
				catch (FileNotFoundException e) {
					System.out.println(e);
				}*/
		
		
		
				LL.add("A");
		    	LL.add("B");
		    	LL.add("C");
		    	
		    	LL2.add("Huh");
		    	LL2.add("IDK");
		    	LL2.add("OK");
		    	
		    	
		    	list.setAll(LL);
		    	list2.setAll(LL2);
		
				//WordDisplayLV.setItems(list);
	}

}