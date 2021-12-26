/*
 * @author Rayane Chelihi Chelouche 
 * @author 51257925X
 */
package model.game;

import java.util.Objects;

import model.Side;
import model.exceptions.InvalidSizeException;
import model.game.score.DestroyedFightersScore;
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
	private String rankings() {
		return "RANKING WINS:  | Player IMPERIAL: " + 	imperial.getWinsScore() + " | Player REBEL: " + rebel.getWinsScore() + " |" + '\n'
	+ "RANKING DESTROYED:  | Player IMPERIAL: "+ imperial.getDestroyedFightersScore() + " | Player REBEL: " + rebel.getDestroyedFightersScore() + " | " + '\n';
	}

	/**
	 * Play.
	 *
	 * @return the side
	 */
	public Side play() {
		imperial.initFighters();
		rebel.initFighters();
		do {
			System.out.println(rankings());
			System.out.println("BEFORE IMPERIAL");
			System.out.println(board.toString() + '\n');
			System.out.print(imperial.showShip());
			System.out.print(rebel.showShip());
			System.out.print("IMPERIAL(" + board.getnum(Side.IMPERIAL) + "):");
			if (!imperial.nextPlay()) {

				return Side.REBEL;
			}
			System.out.println("AFTER IMPERIAL, BEFORE REBEL");
			System.out.println(board.toString() + '\n');

			System.out.print(imperial.showShip());
			System.out.print(rebel.showShip());

			if (imperial.isFleetDestroyed() && imperial.getGameShip().getFleetTest().size() != 0) {
				System.out.println(rankings());
				return Side.REBEL;

			}
			if (rebel.isFleetDestroyed() && rebel.getGameShip().getFleetTest().size() != 0) {
				System.out.println(rankings());
				return Side.IMPERIAL;
			}
			System.out.print("REBEL(" + board.getnum(Side.REBEL) + "):");
			if (!rebel.nextPlay()) {
				System.out.println(rankings());
				return Side.IMPERIAL;
			}
			System.out.println("AFTER REBEL");
			System.out.println(board.toString() + '\n');
			System.out.print(imperial.showShip());
			System.out.print(rebel.showShip());
		/*	if (rebel.getGameShip().getFleetTest().size() == 0)
				return Side.REBEL;
			if (imperial.getGameShip().getFleetTest().size() == 0)
				return Side.IMPERIAL;
			*/
			imperial.purgeFleet();
			rebel.purgeFleet();
		} while (!imperial.isFleetDestroyed() && !rebel.isFleetDestroyed());
		imperial.purgeFleet();
		rebel.purgeFleet();
		System.out.println(rankings());
		if (imperial.isFleetDestroyed()) {
			return Side.REBEL;
		} else {
			return Side.IMPERIAL;
		}
	}
}
