package application.engine;

import application.components.GameBoard;
import application.components.GamePiece;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class EngineBoard {
	
	private char[][] boardPiece = new char[15][15];
	private int[][] boardTileValues = new int[15][15];
	GameBoard gameBoard;
	
	public EngineBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
		for (int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				boardPiece[i][j] = ' ';
				boardTileValues[i][j] = 1;
			}
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/ScrabbleBoard.txt"));
			String line = reader.readLine();
			int row, col, mult;
			while (line != null) {
				String[] specialTile = line.split(" ", 3);
				row = Integer.parseInt(specialTile[0]);
				col = Integer.parseInt(specialTile[1]);
				mult = Integer.parseInt(specialTile[2]);
				if (mult == 3) {
					boardTileValues[row][col] = 3;
				} else if (mult == 2) {
					boardTileValues[row][col] = 2;
				} else {
					boardTileValues[row][col] = 1;
				}
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("file not found");
		}
		this.gameBoard = gameBoard;
		
	}
	
	/**
	 * Initializes EngineBoard's board pieces from save file
	 * @param gameBoard GameBoard GUI object that EngineBaord refers to
	 * @param savedBoard 2D array that holds saved board pieces
	 */
	public EngineBoard(GameBoard gameBoard, char[][] savedBoard) {
		this.gameBoard = gameBoard;
		for (int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				boardPiece[i][j] = savedBoard[i][j]; //TODO
				boardTileValues[i][j] = 1;
			}
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/ScrabbleBoard.txt"));
			String line = reader.readLine();
			int row, col, mult;
			while (line != null) {
				String[] specialTile = line.split(" ", 3);
				row = Integer.parseInt(specialTile[0]);
				col = Integer.parseInt(specialTile[1]);
				mult = Integer.parseInt(specialTile[2]);
				if (mult == 3) {
					boardTileValues[row][col] = 3;
				} else if (mult == 2) {
					boardTileValues[row][col] = 2;
				} else {
					boardTileValues[row][col] = 1;
				}
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("file not found");
		}
		this.gameBoard = gameBoard;
		
	}

	public boolean movePiece(int row, int col) {
		char currentLetter = GameEngine.getCurrentLetter(); 
		if (currentLetter != ' ') {
			if (boardPiece[row][col] == ' ') {
				gameBoard.removeNodeByRowColumnIndex(row, col);
				gameBoard.addPiece(row, col, currentLetter, boardTileValues[row][col]);
				boardPiece[row][col] = currentLetter;
				return true;
			}
		}
		return false;
	}
	
	public void returnBoard(LinkedList<Point> usedTiles) {
		for (int i = 0;  i < usedTiles.size(); i++) {
			int row = usedTiles.get(i).getX();
			int col = usedTiles.get(i).getY();
			gameBoard.addPiece(row, col, ' ', boardTileValues[row][col]);
			boardPiece[row][col] = ' ';
		}
	}
	
}