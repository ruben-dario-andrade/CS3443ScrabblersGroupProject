package application.algo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class WordEngineTemp {
	private char[] focusLetter = new char[15];
	private short focusSize = 0;
	private ArrayList<String> list = new ArrayList<String>();
	BufferedReader reader;
	public WordEngineTemp() {
		
	}
	
	public void addList(LinkedList<String> focus) {
		for (int i = 0; i < 15; i++) {
			focusLetter[i] = ' ';
		}
		for (int i = 0; i < focus.size(); i++) {
			focusLetter[i] = focus.get(i).charAt(0);
		}
		focusSize = (short) focus.size();
	}
	
	public void addLetters(String string) {
		int count = 0;
	}
	
	public void createInclusiveList() {
		list.clear();
		int size, count;
		boolean matchesCriteria;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/ScrabbleDict.txt"));
			String line = reader.readLine();
			line = reader.readLine();
			line = reader.readLine();
			while (line != null) {
				matchesCriteria = true;
				size = line.length();
				count = 0;
				if (line.length() > focusSize + 2) {
					matchesCriteria = false;
				} else { 
					for (int i = 0; i < focusSize; i++) {
						if (line.indexOf(focusLetter[i]) != -1) {
							count++;
						}
					}
					if (count < focusSize + 1) {
						matchesCriteria = true;
					} else {
						matchesCriteria = false;
					}
				}
				if (matchesCriteria) {
					list.add(line);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception e) {
			System.out.println("file not found");
		}
	}
	
	public void removeLettersNotOnBoardOrHand(LinkedList<String> hand, char[][] board) {
		int i, j, size, size2, count;
		boolean flag;
		String line;
		LinkedList<String> existingLetters = new LinkedList<String>();
		ArrayList<String> newList = new ArrayList<String>();
		for (i = 0; i < 15; i++) {
			for (j=0; j < 15; j++) {
				if (!existingLetters.contains(board[i][j]+"") && board[i][j] != ' ') {
					existingLetters.add(board[i][j]+"");
				}
			}
		}
		for (i = 0; i < hand.size(); i++) {
			if (!existingLetters.contains(hand.get(i))) {
				existingLetters.add(hand.get(i));
			}
		}
		
		size = list.size();
		size2 = existingLetters.size();
		for (i = 0; i < size; i++) {
			flag = false;
			line = list.get(i);
			count = 0;
			for (j = 0; j < size2; j++) {
				if (line.indexOf(existingLetters.get(j)) != -1) {
					count++;
				}
			}
			if (count == line.length()) {
				flag = true;
			}
			if (flag) {
				newList.add(line);
			}
		}
		list = newList;
	}
	
	public void removeLettersNotOnHand(LinkedList<String> hand) {
		int i, j, size, size2, count;
		boolean flag;
		String line;
		LinkedList<String> existingLetters = new LinkedList<String>();
		ArrayList<String> newList = new ArrayList<String>();
		for (i = 0; i < hand.size(); i++) {
			if (!existingLetters.contains(hand.get(i))) {
				existingLetters.add(hand.get(i));
			}
		}
		
		size = list.size();
		size2 = existingLetters.size();
		for (i = 0; i < size; i++) {
			flag = false;
			line = list.get(i);
			count = 0;
			for (j = 0; j < size2; j++) {
				if (line.indexOf(existingLetters.get(j)) != -1) {
					count++;
				}
			}
			if (count == line.length()) {
				flag = true;
			}
			if (flag) {
				newList.add(line);
			}
		}
		list = newList;
	}
	
	public LinkedList<String> getCurrentList() {
		LinkedList<String> reccomend = new LinkedList<String>();
		for (int i = 0; i < list.size(); i++) {
			reccomend.add(list.get(i));	
		}
		return reccomend;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
