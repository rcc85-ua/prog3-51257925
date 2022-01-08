/*
 * @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.*;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

/**
 * The Class PlayerRandom.
 */
public class PlayerRandom implements IPlayer {

	/** The num fighters. */
	private int numFighters;

	/** The board. */
	private GameBoard board;

	/** The ship. */
	private GameShip ship;

	/**
	 * Instantiates a new player random.
	 *
	 * @param side        the side
	 * @param numFighters the num fighters
	 */
	public PlayerRandom(Side side, int numFighters) {
		Objects.requireNonNull(side);
		Objects.requireNonNull(numFighters);
		if (side == Side.IMPERIAL) {
			ship = new GameShip("PlayerRandom IMPERIAL Ship", Side.IMPERIAL);
		} else {
			ship = new GameShip("PlayerRandom REBEL Ship", Side.REBEL);
		}
		this.numFighters = numFighters;
	}

	/**
	 * Inits the fighters.
	 */
	@Override
	public void initFighters() {
		int[] aleatorios = { RandomNumber.newRandomNumber(numFighters), RandomNumber.newRandomNumber(numFighters),
				RandomNumber.newRandomNumber(numFighters) };
		StringBuilder constructor = new StringBuilder();
		if (ship.getSide() == Side.IMPERIAL) {
			if (aleatorios[0] != 0) {
				constructor.append(aleatorios[0] + "/TIEFighter:");
			}
			if (aleatorios[1] != 0) {
				constructor.append(aleatorios[1] + "/TIEBomber:");
			}
			if (aleatorios[2] != 0) {
				constructor.append(aleatorios[2] + "/TIEInterceptor");
			}
		} else {
			if (aleatorios[0] != 0) {
				constructor.append(aleatorios[0] + "/XWing:");
			}
			if (aleatorios[1] != 0) {
				constructor.append(aleatorios[1] + "/YWing:");
			}
			if (aleatorios[2] != 0) {
				constructor.append(aleatorios[2] + "/AWing");
			}
		}
		if (constructor.length() != 0) {
			ship.addFighters(constructor.toString());
		}
	}

	/**
	 * Sets the board.
	 *
	 * @param gb the new board
	 */
	@Override
	public void setBoard(GameBoard gb) {
		board = gb;

	}

	/**
	 * Gets the game ship.
	 *
	 * @return the game ship
	 */
	@Override
	public GameShip getGameShip() {
		return ship;
	}

	/**
	 * Checks if is fleet destroyed.
	 *
	 * @return true, if is fleet destroyed
	 */
	@Override
	public boolean isFleetDestroyed() {
		if (ship.getFleetTest().size() > 0) {
			return ship.isFleetDestroyed();
		} else {
			return true;
		}
	}

	/**
	 * Show ship.
	 *
	 * @return the string
	 */
	@Override
	public String showShip() {
		return ship.toString() + '\n' + ship.showFleet();
	}

	/**
	 * Purge fleet.
	 */
	@Override
	public void purgeFleet() {
		ship.purgeFleet();
	}

	/*
	 * public List<Fighter> getFighters(int opcion) { List<Fighter> result = new
	 * ArrayList<Fighter>(); List<Fighter> luchadores = ship.getFleetTest();
	 * 
	 * if (opcion >= 85) { // 98 a 85--> movimiento de mejora for (int i = 0; i <
	 * luchadores.size(); i++) {
	 * 
	 * if (!luchadores.get(i).isDestroyed()) { result.add(luchadores.get(i)); } } }
	 * else if (opcion >= 25) { // 84 a 25 --> launch for (int i = 0; i <
	 * luchadores.size(); i++) { if (!luchadores.get(i).isDestroyed() &&
	 * luchadores.get(i).getPosition() == null) { result.add(luchadores.get(i)); } }
	 * } else { // 0-24--> patrol for (int i = 0; i < luchadores.size(); i++) { if
	 * (!luchadores.get(i).isDestroyed() && luchadores.get(i).getPosition() != null)
	 * { result.add(luchadores.get(i)); } } } if (result.size() == 0) {
	 * System.out.println("ERROR: NO FIGHTERS AVAILABLE"); } return result;
	 * 
	 * }
	 */

	/**
	 * Notab.
	 *
	 * @return the list
	 */
	private List<Integer> notab() {
		List<Integer> luchadores = new ArrayList<Integer>();

		for (int i = 0; i < ship.getFleetTest().size(); i++) {
			if (!ship.getFleetTest().get(i).isDestroyed()) {
				if (ship.getFleetTest().get(i).getPosition() == null) {
					luchadores.add(ship.getFleetTest().get(i).getId());
				} else {
				}
			}
		}
		return luchadores;
	}

	/**
	 * Next play.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean nextPlay() {
		int option = RandomNumber.newRandomNumber(100);
		int id;
		List<Integer> luchadores = null;
		if (option == 99) {
			return false;
		} 
		if (option >= 85) {
			// Coje todos
			luchadores = ship.getFightersId("ship");
		} else if (option >= 25) {

			// Cojeria los que no estan en el tablero
			luchadores = notab();
		} else {
			// Coje los que esten en el tablero
			luchadores = ship.getFightersId("board");
		}
		if (luchadores.size() == 0) {
			System.out.println("ERROR: NO FIGHTERS AVAIBLE");
			return true;
		}
	
		id = luchadores.get(RandomNumber.newRandomNumber(luchadores.size()));
		if (option == 99) {
			return false;
		} else {
			try {
				// Coje que esten o no en el tablero
				// List<Integer> luchadores = ship.getFightersId("ship");
				// movimientode mejora --> mayor que 85
				if (option >= 85) {
					// id = luchadores.get(RandomNumber.newRandomNumber(luchadores.size()));
					ship.improveFighter(id, option, board);
					return true;
					// launch -- > no puede estar en el tablero
				} else if (option >= 25) { // --> entre 85 y 25
					Coordinate c = new Coordinate(RandomNumber.newRandomNumber(board.size),
							RandomNumber.newRandomNumber(board.size));
					try {
						// luchadores = ship.getFightersId("board");
						// id = luchadores.get(RandomNumber.newRandomNumber(luchadores.size()));
						ship.launch(id, c, board);
					} catch (FighterAlreadyInBoardException e) {
						System.out.println("ERROR: Error en el launch de runtime");
						throw new RuntimeException(e);
					} catch (OutOfBoundsException e) {
						System.out.println("ERROR: Error en el launch de runtime");
						throw new RuntimeException(e);
					}
					return true;

					// patrol -- > tiene que estar en el tablero
				} else {
					// id = luchadores.get(RandomNumber.newRandomNumber(luchadores.size()));
					ship.patrol(id, board);
					return true;
				}
			} catch (WrongFighterIdException | FighterNotInBoardException e) {
				System.out.println("ERROR: Runtime exception");
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Gets the wins score.
	 *
	 * @return the wins score
	 */
	@Override
	public WinsScore getWinsScore() {
		return ship.getWinsScore();
	}

	/**
	 * Gets the destroyed fighters score.
	 *
	 * @return the destroyed fighters score
	 */
	@Override
	public DestroyedFightersScore getDestroyedFightersScore() {
		return ship.getDestroyedFightersScore();
	}
}
