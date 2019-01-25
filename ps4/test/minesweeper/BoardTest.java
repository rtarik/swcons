/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import org.junit.Test;

import minesweeper.Board.SquareState;

/**
 * TODO: Description
 */
public class BoardTest {
    
    // Test operations of Board
	private Board board = new Board(10, 10);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testDig() {
    	board.dig(0, 0);
    	assert(board.getSquareState(0, 0) == SquareState.DUG);
    }
    
    @Test
    public void testFlag() {
    	board.flag(1, 0);
    	assert(board.getSquareState(0, 0) == SquareState.FLAGGED);
    }
    
    @Test
    public void testDeflag() {
    	board.deflag(1, 0);
    	assert(board.getSquareState(0, 0) == SquareState.UNTOUCHED);
    }
    
    
}
