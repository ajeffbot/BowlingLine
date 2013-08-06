package Tests;

// Arlo Davidson, Ryan Compton

import static org.junit.Assert.*;

import org.junit.Test;

import Model.BowlingLine;


public class BowlingLineTest {

	@Test
	public void test() {
		BowlingLine game = new BowlingLine();
		game.pinsDowned(5);
		assertEquals(5, game.pinsLeftToDown());
		assertEquals(5, game.scoreSoFar());
		assertFalse(game.canShowScoreFrame(1));
		assertEquals("5  ", game.getRollsForFrame(1));
		game.pinsDowned(2);
		assertEquals("5 2", game.getRollsForFrame(1));
		assertEquals(10, game.pinsLeftToDown());
		assertEquals(7, game.scoreSoFar());
		assertTrue(game.canShowScoreFrame(1));
		assertEquals(7, game.scoreAtFrame(1));
		game.pinsDowned(5);
		assertEquals(5, game.pinsLeftToDown());		
		assertEquals(12, game.scoreSoFar());
		game.pinsDowned(5);
		assertEquals("5 /", game.getRollsForFrame(2));
		assertFalse(game.canShowScoreFrame(2));
		assertEquals(17, game.scoreAtFrame(2));
		game.pinsDowned(2);
		assertEquals(19, game.scoreAtFrame(2));
		assertEquals(21, game.scoreSoFar());
		game.pinsDowned(2);
		assertEquals(23, game.scoreAtFrame(3));
	}
	
	@Test
	public void strikeTest(){
		BowlingLine game = new BowlingLine();
		game.pinsDowned(10);
		assertFalse(game.canShowScoreFrame(1));
		assertEquals("  X", game.getRollsForFrame(1));
		game.pinsDowned(5);
		assertEquals(15, game.scoreAtFrame(1));
		game.pinsDowned(5);
		assertEquals(20, game.scoreAtFrame(1));
		assertEquals(30, game.scoreSoFar());
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(2);
		game.pinsDowned(2);
	}
	
	@Test
	public void perfectGameTest(){
		BowlingLine game = new BowlingLine();
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		assertEquals("XXX", game.getRollsForFrame(10));
		assertEquals(300, game.scoreSoFar());
	}
	
	@Test
	public void perfectfailGameTest(){
		BowlingLine game = new BowlingLine();
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(5);
		game.pinsDowned(5);
		game.pinsDowned(10);
		assertEquals("5/X", game.getRollsForFrame(10));
		assertEquals(275, game.scoreSoFar());
	}
	
	@Test
	public void perfectfailTest2(){
		BowlingLine game = new BowlingLine();
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(5);
		assertEquals("XX5", game.getRollsForFrame(10));
		assertEquals(295, game.scoreSoFar());
	}
	
	@Test
	public void perfectfailTest(){
		BowlingLine game = new BowlingLine();
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(5);
		game.pinsDowned(5);
		assertEquals(0, game.pinsLeftToDown());
		assertEquals("X5/", game.getRollsForFrame(10));
		assertEquals(285, game.scoreSoFar());
	}
	
	@Test
	public void perfectfailTest465(){
		BowlingLine game = new BowlingLine();
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(5);
		game.pinsDowned(4);
		assertEquals(0, game.pinsLeftToDown());
		assertEquals("X54", game.getRollsForFrame(10));
		assertEquals(284, game.scoreSoFar());
	}
	
	@Test
	public void perfectfailTest5(){
		BowlingLine game = new BowlingLine();
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(5);
		assertEquals("5  ", game.getRollsForFrame(10));
		game.pinsDowned(5);
		game.pinsDowned(4);
		assertEquals(0, game.pinsLeftToDown());
		assertEquals("5/4", game.getRollsForFrame(10));
	}
	
	@Test
	public void perfectfailfinalTest(){
		BowlingLine game = new BowlingLine();
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(10);
		game.pinsDowned(2);
		game.pinsDowned(5);
		assertEquals(0, game.pinsLeftToDown());
		assertEquals("2 5", game.getRollsForFrame(10));
	}
	@Test
	public void perfectfailfinalTestAgain(){
		BowlingLine game = new BowlingLine();
		game.pinsDowned(9);
		game.pinsDowned(0);
	}
}
