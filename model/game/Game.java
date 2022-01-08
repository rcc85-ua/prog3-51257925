/*
 * @author Rayane Chelihi Chelouche 
 * @author 51257925X
 */
package model.game;

import java.util.Objects;

import model.Side;
import model.exceptions.InvalidSizeException;
import model.game.score.DestroyedFightersScore;
import model.game.score.Ranking;
import model.game.score.WinsScore;
/**
 * The Class Game.
 */
public class Game {

	/** The board size. */
	static int BOARD_SIZE = 10;

	/** The rebel. */
	private IPlayer rebel;

	/** The imperial. */
	private IPlayer imperial;

	/** The board. */
	private GameBoard board;

	/**
	 * Instantiates a new game.
	 *
	 * @param imperial the imperial
	 * @param rebel    the rebel
	 */
	public Game(IPlayer imperial, IPlayer rebel) {
		Objects.requireNonNull(rebel);
		Objects.requireNonNull(imperial);
		this.imperial = imperial;
		this.rebel = rebel;
		try {
			board = new GameBoard(BOARD_SIZE);
		} catch (InvalidSizeException e) {
			throw new RuntimeException();
		}
		imperial.setBoard(board);
		rebel.setBoard(board);
	}

	/**
	 * Gets the game board.
	 *
	 * @return the game board
	 */
	public GameBoard getGameBoard() {
		return board;
	}
	
	/**
	 * Rankings.
	 *
	 * @return the string
	 */
	private void rankings() {
		Ranking<WinsScore> ganadas= new Ranking<WinsScore>();
		Ranking<DestroyedFightersScore> destruidas= new Ranking<DestroyedFightersScore>();
		
		ganadas.addScore(imperial.getWinsScore());
		destruidas.addScore(imperial.getDestroyedFightersScore());
		ganadas.addScore(rebel.getWinsScore());
		destruidas.addScore(rebel.getDestroyedFightersScore());
		
		System.out.println( "RANKING WINS: " + ganadas.toString() );
		System.out.println("RANKING DESTROYED: "+ destruidas.toString());
	}

	/**
	 * Play.
	 *
	 * @return the side
	 */
public Side play() {
		
		Side side = null;
		int  n = 0;
		boolean jugar = true;
		
		
		imperial.initFighters();
		imperial.setBoard(board);
		rebel.initFighters();
		rebel.setBoard(board);
		
		while(jugar == true) {
			rankings();
			System.out.println("BEFORE IMPERIAL");
			System.out.println(board.toString());
			System.out.println(imperial.showShip()+ "\n" + rebel.showShip());
			n = board.numFighters(Side.IMPERIAL);
			
			System.out.print("IMPERIAL(" + n +"):" );
			
			if(!imperial.nextPlay()) {
			
				side = Side.REBEL;
				
				jugar = false;
				
				break;
			}
			
			System.out.println("AFTER IMPERIAL, BEFORE REBEL");
			System.out.println(board.toString());
			System.out.println(imperial.showShip()+ "\n" + rebel.showShip());
			
			if(imperial.isFleetDestroyed()) { 
				
				side = Side.REBEL;
				
				jugar = false;
				break;
				
			}else if(rebel.isFleetDestroyed()) {
				
				side = Side.IMPERIAL;
				
				jugar = false;
				break;
			}
			
			n = board.numFighters(Side.REBEL);
			
			System.out.print("REBEL(" + n +"):" );
			
			
			
			if(!rebel.nextPlay()) {
				
				side = Side.IMPERIAL;
				jugar = false;
				break;
			}
			
			System.out.println("AFTER REBEL");
			System.out.println(board.toString());
			System.out.println(imperial.showShip()+ "\n" + rebel.showShip());
			imperial.purgeFleet();
			rebel.purgeFleet();
			
			if(imperial.isFleetDestroyed()) { 
				
				side = Side.REBEL;
				
				jugar = false;
				
			}else if(rebel.isFleetDestroyed()) {
				
				side = Side.IMPERIAL;
				
				jugar = false;
			}
			
		}
		
		imperial.purgeFleet();
		rebel.purgeFleet();
		rankings();
		return side;
	}
}
