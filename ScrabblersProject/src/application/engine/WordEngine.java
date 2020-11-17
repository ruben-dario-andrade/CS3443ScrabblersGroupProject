package application.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class WordEngine {
	private char[] focusLetter = new char[15];
	private short focusSize = 0;
	private ArrayList list = new ArrayList();
	
	public WordEngine() {
		
	}
	
	public void addList(LinkedList<String> focus) {
		for (int i = 0; i < focus.size(); i++) {
			focusLetter[i] = focus.get(i).charAt(0);
			focusSize++;
		}
	}
	
	public void createInclusiveList() {
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
	
	
	
	public ArrayList getCurrentList() {
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		return list;
	}
	
}
