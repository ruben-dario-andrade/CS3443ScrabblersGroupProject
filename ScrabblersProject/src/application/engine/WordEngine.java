package application.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class WordEngine {
	private char[] focusLetter = new char[15];
	private short focusSize = 0;
	private ArrayList<String> list = new ArrayList<String>();
	BufferedReader reader;
	public WordEngine() {
		
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
	
	public void createInclusiveList() {
		try {
			this.reader = new BufferedReader(new FileReader("res/ScrabbleDict.txt"));
			list.clear();
			String line = this.reader.readLine();
			line = this.reader.readLine();
			line = this.reader.readLine();
			while (line != null) {
				boolean matchesCriteria = true;
				for (int i = 0; i < focusSize; i++) {
					if (line.indexOf(focusLetter[i]) == -1) {
						matchesCriteria = false;
					}
				}
				if (matchesCriteria) {
					list.add(line);
				}
				line = this.reader.readLine();
			}
			this.reader.close();
		} catch(Exception e) {
			System.out.println("file not found");
			e.printStackTrace();
			try {
				this.reader.close();
			} catch (IOException e1) {
				System.out.println("couldn't close");
				e1.printStackTrace();
			}
		}
	}
	
	public void createExclusiveList() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/ScrabbleDict.txt"));
			String line = reader.readLine();
			line = reader.readLine();
			line = reader.readLine();
			while (line != null) {
				boolean matchesCriteria = true;
				for (int i = 0; i < focusSize; i++) {
					if (line.indexOf(focusLetter[i]) == -1) {
						matchesCriteria = false;
					}
					if (line.length() > focusSize) {
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
	
	
	
	public LinkedList<String> getCurrentList() {
		LinkedList<String> reccomend = new LinkedList<String>();
		for (int i = 0; i < list.size(); i++) {
			reccomend.add(list.get(i));	
		}
		return reccomend;
	}
	
}
