package application.algo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import application.engine.Point;

public class WordVerification {
	
	/*
	 * Parameters:
	 * 		LinkedList<String> testWords - linked list of words to be tested
	 * Return:
	 * 		true if all words in list were found in dictionary
	 * Description:
	 * 		Check words in list against Scrabble dictionary
	 * */
	public static boolean inDictionary(LinkedList<String> testWords) {
		int verifiedWordCount = 0;
		int size = testWords.size();
		int i;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/ScrabbleDict2.txt"));
			String line = reader.readLine();
			while (line != null) {
				for (i = 0; i < size; i++) {
					if (line.equals(testWords.get(i))) {
						verifiedWordCount++;
					}
				}
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception e) {
			System.out.println("file not found");
			e.printStackTrace();
		}
		if (verifiedWordCount == size) {
			return true;
		}
		return false;
	}

	/*
	 * Parameters:
	 * 		char[][] board - board of current letters
	 *  	LinkedList<String> usedLetters - usedLetters in the current turn
	 * 		LinkedList<Point> usedTiles - usedTiles in the current turn
	 * Return:
	 * 		LinkedList<String> of found words through the function
	 * Description:
	 * 		Test user input for continuity, correct branches, and if input is on same column
	 * */
	public static LinkedList<String> validWord(char[][] board, LinkedList<String> usedLetters, LinkedList<Point> usedTiles) {
		boolean isHorizontal;
		LinkedList<String> foundStrings = new LinkedList<String>();
		StringBuilder mainString;
		String temp1;
		String temp2;
		if (usedLetters.size() == 0) {
			return null;
		} else if (usedLetters.size() == 1) {
			if (!checkIfBranched(board, usedLetters, usedTiles, true, usedTiles.get(0).getY()) && !checkIfBranched(board, usedLetters, usedTiles, false, usedTiles.get(0).getX())) {
				return null;
			}
			temp1 = getWordClumps(board, usedLetters, usedTiles, true, usedTiles.get(0).getY ());
			temp2 = getWordClumps(board, usedLetters, usedTiles, false, usedTiles.get(0).getX());
			if (temp1.length() > 1) {
				foundStrings.add(temp1);
			}
			if (temp2.length() > 1) {
				foundStrings.add(temp2);
			}
			if (temp1.length() < 2 && temp2.length() < 2) {
				return null;
			} else {
				return foundStrings;
			}
		} else {
			if (usedTiles.get(0).getX() == usedTiles.get(1).getX()) {
				int xFile = usedTiles.get(0).getX();
				for (int i = 2; i < usedTiles.size(); i++) {
					if (usedTiles.get(i).getX() != xFile) {
						return null;
					}
				} 
				isHorizontal = false;
				if (!checkIfBranched(board, usedLetters, usedTiles, isHorizontal, xFile)) {
					return null;
				}
				if (!checkIfContinuous(board, usedTiles, isHorizontal, xFile)) {
					return null;
				}
				temp1 = getWordClumps(board, usedLetters, usedTiles, isHorizontal, xFile);
				foundStrings.add(temp1);
				return foundStrings;
			} else if (usedTiles.get(0).getY() == usedTiles.get(1).getY()){
				int yFile = usedTiles.get(0).getY();
				for (int i = 2; i < usedTiles.size(); i++) {
					if (usedTiles.get(i).getY() != yFile) {
						return null;
					}
				} 
				isHorizontal = true;
				if (!checkIfBranched(board, usedLetters, usedTiles, isHorizontal, yFile)) {
					return null;
				}
				if (!checkIfContinuous(board, usedTiles, isHorizontal, yFile)) {
					return null;
				}
				temp1 = getWordClumps(board, usedLetters, usedTiles, isHorizontal, yFile);
				foundStrings.add(temp1);
				return foundStrings;
			} else {
				return null;
			}
		}
	}
	
	/*
	 * Parameters:
	 * 		char[][] board - board of current letters
	 *  	LinkedList<String> usedLetters - usedLetters in the current turn
	 * 		LinkedList<Point> usedTiles - usedTiles in the current turn
	 * Return:
	 * 		Possible additional words formed by the user
	 * Description:
	 * 		Checks along row/column for possible new words formed by the user
	 * */
	public static LinkedList<String> searchRemaining(char[][] board, LinkedList<String> usedLetters, LinkedList<Point> usedTiles){
		LinkedList<String> tempList = new LinkedList<String>();
		String temp1;
		String temp2;
		int size = usedTiles.size();
		if (size > 1) {
			for (int i = 0; i < size; i++) {
				temp1 = getWordClumps(board, usedLetters, usedTiles, true, usedTiles.get(i).getY ());
				temp2 = getWordClumps(board, usedLetters, usedTiles, false, usedTiles.get(i).getX());
				if (temp1.length() > 1) {
					tempList.add(temp1);
				}
				if (temp2.length() > 1) {
					tempList.add(temp2);
				}
			}
		}
		String maxDuplicates = "";
		int maxCount = 0;
		for (int i = 0; i < tempList.size(); i++) {
			int count = 0;
			String hold = tempList.get(i);
			for (int j = 0; j < tempList.size(); j++) {
				if (hold.equals(tempList.get(j))) {
					count++;
				}
			}
			if (count > maxCount) {
				maxDuplicates = hold;
				maxCount = count;
			}
		}
		tempList.size();
		for (int i = 0; i < tempList.size(); i++) {
			if (tempList.get(i).equals(maxDuplicates)) {
				tempList.remove(i);
				i--;
			}
		}
		
		return tempList;
	}
	
	
	/*
	 * Parameters:
	 * 		char[][] board - board of current letters
	 *  	LinkedList<String> usedLetters - usedLetters in the current turn
	 * 		LinkedList<Point> usedTiles - usedTiles in the current turn
	 * 		boolean isHorizontal - if word is horizontal
	 * 		int file - row or column word is on
	 * Return:
	 * 		String of found word along the file
	 * Description:
	 * 		Identifies word the user placed
	 * */
	private static String getWordClumps(char[][] board, LinkedList<String> usedLetters, LinkedList<Point> usedTiles, boolean isHorizontal, int file){
		StringBuilder string = new StringBuilder();
		int min, max, size, hold;
		max = -1;
		min = 15;
		size = usedTiles.size();
		for (int i = 0; i < size; i++) {
			if (isHorizontal) {
				hold = usedTiles.get(i).getX();
			} else {
				hold = usedTiles.get(i).getY();
			}
			if (hold > max) {
				max = hold;
			}
			if (hold < min) {
				min = hold;
			}
		}
		if (isHorizontal) {
			for (int i = 0; i < 15; i++) {
				if (board[i][file] != ' ') {
					if (i <= max && i >= min) {
						string.append(board[i][file]);
					}
				}
			}
			boolean flag = true;
			if (min >= 1) {
				for (int i = min - 1; i >= 0; i--) {
					if (board[i][file] != ' ' && flag) {
						string.insert(0, board[i][file]);
					} else {
						flag = false;
					}
				}
			}
			flag = true;
			if (max <= 13) {
				for (int i = max + 1; i <= 14; i++) {
					if (board[i][file] != ' ' && flag) {
						string.append(board[i][file]);
					} else {
						flag = false;
					}
				}
			}
		} else {
			for (int i = 0; i < 15; i++) {
				if (board[file][i] != ' ') {
					if (i <= max && i >= min) {
						string.append(board[file][i]);
					}
				}
			}
			boolean flag = true;
			if (min >= 1) {
				for (int i = min - 1; i >= 0; i--) {
					if (board[file][i] != ' ' && flag) {
						string.insert(0, board[file][i]);
					} else {
						flag = false;
					}
				}
			}
			flag = true;
			if (max <= 13) {
				for (int i = max + 1; i <= 14; i++) {
					if (board[file][i] != ' ' && flag) {
						string.append(board[file][i]);
					} else {
						flag = false;
					}
				}
			}
		}
		return string.toString();
	}
	
	/*
	 * Parameters:
	 * 		char[][] board - board of current letters
	 * 		LinkedList<Point> usedTiles - usedTiles in the current turn
	 * 		boolean isHorizontal - if word is horizontal
	 * 		int file - row or column word is on
	 * Return:
	 * 		true if the word is continuous
	 * Description:
	 * 		Check if the word is connected to itself
	 * */
	private static boolean checkIfContinuous(char[][] board, LinkedList<Point> usedTiles, boolean isHorizontal, int file) {
		int min, max, size;
		boolean flag;
		max = -1;
		min = 15;
		size = usedTiles.size();
		if (isHorizontal) {
			for (int i = 0; i < size; i++) {
				int x = usedTiles.get(i).getX();
				if (x > max) {
					max = x;
				}
				if (x < min) {
					min = x;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				int y = usedTiles.get(i).getY();
				if (y > max) {
					max = y;
				}
				if (y < min) {
					min = y;
				}
			}
		}
		if (max - min + 1 == size) {
			return true;
		} else if (max - min + 1 > size){
			if (isHorizontal) {
				for (int i = min; i < max; i++) {
					if (board[i][file] == ' ') {
						return false;
					}
				}
			} else {
				for (int i = min; i < max; i++) {
					if (board[file][i] == ' ') {
						return false;
					}
				}
			}
			return true;
		} else {
			return false;
		}
		
	}
	
	/*
	 * Parameters:
	 * 		char[][] board - board of current letters
	 * 		LinkedList<String> usedLetters - usedLetters in the current turn
	 * 		LinkedList<Point> usedTiles - usedTiles in the current turn
	 * 		boolean isHorizontal - if word is horizontal
	 * 		int file - row or column word is on
	 * Return:
	 * 		true if the word is properly branched
	 * Description:
	 * 		Check if the word is connected to the center or to another word
	 * */
	private static boolean checkIfBranched(char[][] board, LinkedList<String> usedLetters, LinkedList<Point> usedTiles, boolean isHorizontal, int file) {
		int min, max, size;
		max = -1;
		min = 15;
		size = usedTiles.size();
		if (isHorizontal) {
			for (int i = 0; i < size; i++) {
				int x = usedTiles.get(i).getX();
				if (x > max) {
					max = x;
				}
				if (x < min) {
					min = x;
				}
			}
			if (max != 14) {
				if (board[max + 1][file] != ' ') {
					return true;
				}
			}
			if (min != 0) {
				if (board[min - 1][file] != ' ') {
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				int y = usedTiles.get(i).getY();
				if (y > max) {
					max = y;
				}
				if (y < min) {
					min = y;
				}
			}
			if (max != 14) {
				if (board[file][max + 1] != ' ') {
					return true;
				}
			}
			if (min != 0) {
				if (board[file][min - 1] != ' ') {
					return true;
				}
			}
		}
		for (int i = 0; i < size; i++) {
			if (usedTiles.get(i).getX() == 7 && usedTiles.get(i).getY() == 7) {
				return true;
			} 
		}
		if (max - min + 1 > size){
			if (isHorizontal) {
				for (int i = min; i < max; i++) {
					if (board[i][file] == ' ') {
						return false;
					}
				}
			} else {
				for (int i = min; i < max; i++) {
					if (board[file][i] == ' ') {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	
}















