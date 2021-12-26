/*
 * @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import model.*;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

/**
 * The Class PlayerFile.
 */
public class PlayerFile implements IPlayer {
	
	/** The br. */
	private BufferedReader br;
	
	/** The ship. */
	private GameShip ship;
	
	/** The board. */
	private GameBoard board;

	/**
	 * Instantiates a new player file.
	 *
	 * @param side the side
	 * @param br the br
	 */
	public PlayerFile(Side side, BufferedReader br) {
		Objects.requireNonNull(side);
		Objects.requireNonNull(br);	
		if (side == Side.IMPERIAL) {
			ship = new GameShip("PlayerFile IMPERIAL Ship", Side.IMPERIAL);
		} else {
			ship = new GameShip("PlayerFile REBEL Ship", Side.REBEL);
		}
		this.br = br;
	}

	/**
	 * Sets the board.
	 *
	 * @param gb the new board
	 */
	@Override
	public void setBoard(GameBoard gb) {
		Objects.requireNonNull(gb);
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
	 * Inits the fighters.
	 */
	@Override
	public void initFighters() {
		String luchadores;
		try {
			luchadores = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException();
		}
		ship.addFighters(luchadores);
	}

	/**
	 * Checks if is fleet destroyed.
	 *
	 * @return true, if is fleet destroyed
	 */
	@Override
	public boolean isFleetDestroyed() {
		boolean resultado = true;
		for(int i = 0; i<ship.getFleetTest().size();i++) {
			if(!ship.getFleetTest().get(i).isDestroyed()) {
				resultado = false;
			}
		}
		return resultado;
	}

	/**
	 * Show ship.
	 *
	 * @return the string
	 */
	@Override
	public String showShip() {
		return ship.toString() + '\n' + ship.showFleet() + '\n';
	}

	/**
	 * Purge fleet.
	 */
	@Override
	public void purgeFleet() {
		ship.purgeFleet();
	}

	/**
	 * Fragmentador.
	 *
	 * @param br2 the br 2
	 * @return the int
	 */
	// Pasarlo a string dividirlo en segmentos
	public int fragmentador(String br2) {
		String partes[] = br2.split(" ");

		switch (partes[0]) {
		case "exit":
			return 1;
		case "improve":
			if (partes.length == 3) {
				int id = Integer.parseInt(partes[1]);
				int qty = Integer.parseInt(partes[2]);
				if (qty < 100) {
					try {
						ship.improveFighter(id, qty, board);
					} catch (WrongFighterIdException e) {
						System.out.println("ERROR: en el improve");
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("ERROR: QTY EXCEEDS THE MAXIMUM TAM");
				}
			} else {
				System.out.println("ERROR: INVALID COMMAND");
			}
			return 2;
		case "patrol":
			if (partes.length == 2) {
				try {
					ship.patrol(Integer.parseInt(partes[1]), board);
				} catch (WrongFighterIdException |  FighterNotInBoardException e) {
					System.out.println("ERROR: en el patrol");
					System.out.println("ERROR: " + e.getMessage());
				}
			} else {
				System.out.println("ERROR: INVALID COMMAND");
			}
			return 3;
		case "launch":
			if (partes.length == 3) {
				try {
					Fighter luchador = ship.getFirstAvailableFighter("");
					Coordinate c = new Coordinate(Integer.parseInt(partes[1]), Integer.parseInt(partes[2]));
					try {
						ship.launch(luchador.getId(), c, board);
					} catch (Exception e) {
						System.out.println("ERROR: en el launch");
						System.out.println(e.getMessage());
					}
				} catch (NoFighterAvailableException e) {
					//System.out.print("ERROR: en el launch");
					System.out.println(e.getMessage());
				}
			} else if (partes.length == 4) {
				try {
					int i = Integer.parseInt(partes[3]);
					Coordinate c = new Coordinate(Integer.parseInt(partes[1]), Integer.parseInt(partes[2]));
					try {
						ship.launch(i, c, board);
					} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException e) {
						System.out.println("ERROR: Error en el launch");
						System.out.println(e.getMessage());
					}
				} catch (NumberFormatException e) {
					try {
						Fighter luchador = ship.getFirstAvailableFighter(partes[3]);
						Coordinate c = new Coordinate(Integer.parseInt(partes[1]), Integer.parseInt(partes[2]));
						try {
							ship.launch(luchador.getId(), c, board);
						} catch (Exception e1) {
							System.out.println("ERROR: error en el launch");
							System.out.println(e1.getMessage());
						}
					} catch (NoFighterAvailableException e1) {
						System.out.println("ERROR: en el launch");
						System.out.println(e1.getMessage());
					}
				}
			} else {
				System.out.println("ERROR INVALID COMMAND");
			}
			return 4;
		default:
			System.out.println("ERROR: INVALID COMMAND");
			return 5;
		}

	}

	/**
	 * Next play.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean nextPlay() {
		String opciones;
		try {
			opciones = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException();
		}

		switch (fragmentador(opciones)) {
		case 1:
			return false;
		case 2:
		case 3:
		case 4:
		case 5:
			return true;
		default:
			return true;
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
