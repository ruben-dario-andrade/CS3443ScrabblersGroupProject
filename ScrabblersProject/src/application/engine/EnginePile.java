package application.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;

public class EnginePile {

	private LinkedList<String> pileLetters;
	
	public EnginePile() {
		pileLetters = new LinkedList<String>();
		pileLetters = retrievePilePieces();
		Collections.shuffle(pileLetters);
	}
	
	/**
	 * Initialize game pile with saved pile pieces
	 * @param savedPile LinkedList holding saved pile pieces
	 */
	public EnginePile(LinkedList<String> savedPile) {
		pileLetters = savedPile;
	}

	/*
	 * Description:
	 * 		Retrieves pile from res folder text file 
	 * */
	private static LinkedList<String> retrievePilePieces(){
		LinkedList<String> pile = new LinkedList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/ScrabblePile.txt"));
			String line = reader.readLine();
			while (line != null) {
				for (int i = 0; i < line.length(); i++) {
					pile.add(line.charAt(i)+"");
				}
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception e) {
			System.out.println("file not found");
		}
		return pile;
	}
	
	/*
	 * Parameters:
	 * 		LinkedList<String> list - list to be added to pile
	 * Description:
	 * 		Adds list and then shuffles pile
	 * */
	public void commitList(LinkedList<String> list) {
		int size = list.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				pileLetters.add(list.get(i));
			}
		}
		Collections.shuffle(pileLetters);
	}
	
	/*
	 * Parameters:
	 * 		String letter - letter that is added to pile
	 * */
	public void addLetter(String letter) {
		pileLetters.add(letter);
	}
	
	/*
	 * Return:
	 * 		popped letter of pile
	 */
	public String popLetter() {
		if (pileLetters.size() > 0) {
			return pileLetters.pop();
		}
		return "";
	}
	
	
	/*
	 * Return:
	 * 		int size of pile
	 */
	public int getSize() {
		return pileLetters.size();
	}
	
	/**
	 * Getter function for game pile
	 * @return game pile
	 */
	public LinkedList<String> getPile() {
		return this.pileLetters;
	}
}
