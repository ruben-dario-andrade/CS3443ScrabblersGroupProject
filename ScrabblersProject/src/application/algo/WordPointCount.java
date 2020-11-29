package application.algo;

import java.util.LinkedList;

import application.engine.EngineBoard;
import application.engine.Point;

public class WordPointCount {
	
	public static int getCharScore(char c) {
    	switch(c) {
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
	    		return 1;
	    	}
	    	case 'd':
	    	case 'g': {
	    		return 2;
	    	}
	    	case 'b':
	    	case 'c':
	    	case 'm':
	    	case 'p': {
	    		return 3;
	    	}
	    	case 'f':
	    	case 'h':
	    	case 'v':
	    	case 'w':
	    	case 'y': {
	    		return 4;
	    	}
	   		case 'k':
	   			return 5;
	   		case 'j':
    		case 'x': {
    			return 8;
	    		}
	    	case 'q':
	    	case 'z': {
	    		return 10;
	    	}  			
    		
    	}
    	return 0;
	}
	
	public static void countPoints(LinkedList<Point> usedTiles, LinkedList<String> foundStrings, char[][] board) {
		int pointTotal = 0;
		int row, col, size;
		String temp;
		int[][] boardValues = EngineBoard.getBoardValues();
		for (int i = 0; i < usedTiles.size(); i++) {
			row = usedTiles.get(i).getX();
			col = usedTiles.get(i).getY();
			pointTotal += getCharScore(board[row][col]) * boardValues[row][col];
		}
		size = foundStrings.size();
		if (size > 1) {
			for (int i = 1; i < size; i++) {
				temp = foundStrings.get(i);
				for (int j = 0; j < temp.length(); j++) {
					pointTotal += getCharScore(temp.charAt(i));
				}
			}
		}
	}
	
}
