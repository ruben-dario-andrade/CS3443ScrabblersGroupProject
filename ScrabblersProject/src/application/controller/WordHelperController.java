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
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class WordHelperController implements Initializable {
	
	@FXML
	private BorderPane WordHelperPane;
	
	@FXML
    private TextField letterSearch;
	
   @FXML
    private RadioButton OnlyMyLettersTB;

    @FXML
    private RadioButton AllWordsTB;
    
    @FXML
    private TextField pointsTF;
    
    @FXML
    private AnchorPane SavePane;
	
	@FXML
    private ListView<String> WordDisplayLV = new ListView<String>();
	
	WordThread wordThread;
	
    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<String> list2 = FXCollections.observableArrayList();


    LinkedList<String> LL = new LinkedList<String>();
    LinkedList<String> LL2 = new LinkedList<String>();

    
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

	
    public int assignScore(String word) {
    	int total = 0;
    	
    	for(int i = 0; i < word.length(); i++) {
    		char letter = word.toLowerCase().charAt(i);
    		
    		switch(letter) {
	    		case 'a':
	    		case 'e':
	    		case 'i':
	    		case 'l':
	    		case 'n':
	    		case 'o':
	    		case 'r':
	    		case 's':
	    		case 't':
	    		case 'u': {
	    			total = total + 1;
	    			break;
	    		}
	    		case 'd':
	    		case 'g': {
	    			total = total + 2;
	    			break;
	    		}
	    		case 'b':
	    		case 'c':
	    		case 'm':
	    		case 'p': {
	    			total = total + 3;
	    			break;
	    		}
	    		case 'f':
	    		case 'h':
	    		case 'v':
	    		case 'w':
	    		case 'y': {
	    			total = total + 4;
	    			break;
	    		}
	    		case 'k':
	    			total = total + 5;
	    			break;
	    		case 'j':
	    		case 'x': {
	    			total = total + 8;
	    			break;
	    		}
	    		case 'q':
	    		case 'z': {
	    			total = total + 10;
	    			break;
	    		}
	 
	    			
    		}
    	}
    	
    	return total;
    }
    
    
	@FXML
	public void populateLL(ActionEvent event) {
		if(AllWordsTB.isSelected()) {
			if(letterSearch == null) {
				// ** LinkedList below should be for all possible words (includes any/all letters) **
				LinkedList<String> LL3 = wordThread.getReccomendations();
		    	list.setAll(LL3);
				WordDisplayLV.setItems(list);

			} else {
				String letters = letterSearch.getText().toString();
				
				wordThread.onlyS(letters);
				wordThread.filterWords();

				//	GameModel.---fake name---(letters);
				// letters variable would then be pushed into function to return words with
				// ** LL below should be same as above but with letters from letterSearch Text Field **
				LinkedList<String> LL3 = wordThread.getReccomendations();
		    	list.setAll(LL3);
				WordDisplayLV.setItems(list);
			}
		}
		else {

			if(letterSearch == null) {

				// ** LinkedList below should be made up of words that can only be made from the player's hand **
				LinkedList<String> LL3 = wordThread.getReccomendations();
		    	list.setAll(LL3);
				WordDisplayLV.setItems(list);

			} else {
				String letters = letterSearch.getText().toString();
				wordThread.addS(letters);
				wordThread.filterWords();

				//	GameModel.---fake name---(letters);
				// letters variable would then be pushed into function to return words with
				// ** LL below should be same as above but with letters from letterSearch Text Field **
				LinkedList<String> LL3 = wordThread.getReccomendations();
		    	list.setAll(LL3);
				WordDisplayLV.setItems(list);

			}
		}
		

	}
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.wordThread = new WordThread();
		//HelperModel.start(wordThread);
		//wordThread.end();

		WordDisplayLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldVal, String totalPoints) {
		        pointsTF.setText(String.valueOf(assignScore(totalPoints)));
		    }
		});
	}
}



	


















