package application.algo;

import java.util.LinkedList;

import application.controller.WordHelperController;
import application.engine.GameEngine;

public class WordThread {
	
	WordEngineTemp wordEngine;	
	LinkedList<String> reccomendations;
	
	boolean running = false;
  	
	public WordThread() {
		wordEngine = new WordEngineTemp();	
		this.reccomendations = new LinkedList<String>();
		//this.wordEngine = new WordEngine();
  	}

  	public void addS(String s) {
  		//wordEngine.addString(s);
  	}
  	
  	public void onlyS(String s) {
  		//wordEngine.onlyString(s);
  	}
  	
  	public void filterWords() {
  		wordEngine.addList(GameEngine.getHand());
  		wordEngine.createInclusiveList();
  		wordEngine.removeLettersNotOnBoardOrHand(GameEngine.getHand(), GameEngine.getBoard());
  		this.reccomendations = wordEngine.getCurrentList();
  		//wordEngine.clearLists();
  	}
  	
  	public LinkedList<String> getReccomendations() {
  		return this.reccomendations;
  	}

}