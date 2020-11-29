package application.engine;

/* This is a utility class to keep track of points */

public class Point {
	private int x;
	private int y;
	
	
	/*
	 * Description:
	 * 		Sets x and y
	 * */
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Return:
	 * 		int x value
	 * */
	public int getX() {
		return x;
	}
	
	/*
	 * Return:
	 * 		int y value
	 * */
	public int getY() {
		return y;
	}
}
