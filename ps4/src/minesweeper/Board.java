/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

/**
 * TODO: Specification
 */
public class Board {
    
	// Abstraction function:
	//	represents a sizeX by sizeY Minesweeper board
	// Rep invariant:
	// 	TODO
	// Safety from rep exposure:
	//	TODO
	// Thread Safety:
	//	TODO
    
    // TODO: Specify, test, and implement in problem 2
	
	private int sizeX;
	private int sizeY;
	
	public Board(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	public enum SquareState {
		UNTOUCHED, DUG, FLAGGED
	}
	
	/**
	 * Convert a minesweeper board to a String representation
	 * @return String representation of the board
	 */
	public String look() {
		return null;
	}
	
	/**
	 * Dig square (x, y)
	 * @param x x coordinate of the square, requires 0 <= x < sizeX
	 * @param y y coordinate of the square, requires 0 <= y < sizeY
	 */
	public void dig(int x, int y) {
		
	}
	
	/**
	 * Flag square (x, y)
	 * @param x x coordinate of the square, requires 0 <= x < sizeX
	 * @param y y coordinate of the square, requires 0 <= y < sizeY
	 */
	public void flag(int x, int y) {
		
	}
	
	/**
	 * Deflag square (x, y)
	 * @param x x coordinate of the square, requires 0 <= x < sizeX
	 * @param y y coordinate of the square, requires 0 <= y < sizeY
	 */
	public void deflag(int x, int y) {
		
	}
	
	/**
	 * Get square state
	 * @param x x coordinate of the square, requires 0 <= x < sizeX
	 * @param y y coordinate of the square, requires 0 <= y < sizeY
	 */
	public SquareState getSquareState(int x, int y) {
		return SquareState.UNTOUCHED;
	}
    
}
