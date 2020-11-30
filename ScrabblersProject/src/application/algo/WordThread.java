package application.algo;

import java.util.LinkedList;

import application.controller.WordHelperController;
import application.engine.GameEngine;

public class WordThread {
	
	WordEngineTemp wordEngine;	
	LinkedList<String> reccomendations;
	
	boolean running = false;
	boolean toggle = false;
  	
	public WordThread() {
		wordEngine = new WordEngineTemp();	
		this.reccomendations = new LinkedList<String>();
		//this.wordEngine = new WordEngine();
  	}

  	public void addS(String s) {
  		//wordEngine.addString(s);
  		toggle = false;
  	}
  	
  	public void onlyS(String s) {
  		toggle = true;
  	}
  	
  	public void filterWords() {
  		wordEngine.addList(GameEngine.getHand());
  		wordEngine.createInclusiveList();
  		if (toggle) {
  			wordEngine.removeLettersNotOnBoardOrHand(GameEngine.getHand(), GameEngine.getBoard());
  		} else {
  			wordEngine.removeLettersNotOnHand(GameEngine.getHand());
  		}
  		
  		this.reccomendations = wordEngine.getCurrentList();
  		//wordEngine.clearLists();
  	}
  	
  	public LinkedList<String> getReccomendations() {
  		return this.reccomendations;
  	}

}