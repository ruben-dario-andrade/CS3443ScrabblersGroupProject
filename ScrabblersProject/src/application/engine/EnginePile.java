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
		
		//System.out.println("Engine Pile - Overloaded Constructor: ");
		for(int i = 0; i < pileLetters.size(); i++) {
			if(i != 0 && !pileLetters.get(i).equals(pileLetters.get(i-1))) {
				//System.out.print("\n" + pileLetters.get(i));
			} else {
				//System.out.print(pileLetters.get(i));
			}
		}
		//System.out.println();
		
		Collections.shuffle(pileLetters);
	}

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
	
	public void commitList(LinkedList<String> list) {
		int size = list.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				pileLetters.add(list.get(i));
			}
		}
		Collections.shuffle(pileLetters);
	}
	
	public void addLetter(String letter) {
		pileLetters.add(letter);
	}
	
	public String popLetter() {
		return pileLetters.pop();
	}
	
	
	
	public int getSize() {
		return pileLetters.size();
	}
	
	
	
}
