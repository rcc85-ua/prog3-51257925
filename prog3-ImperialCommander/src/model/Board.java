/*
 * @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import model.exceptions.*;

/**
 * The Class Board.
 */

public class Board {

	/** The size. */
	private int size;

	/** The board. */
	protected Map<Coordinate, Fighter> board;

	
	
	
	/**
	 * Gets the num.
	 *
	 * @param side the side
	 * @return the num
	 */
	public int getnum(Side side) {
		int contador = 0;
		if(side != null) {
			for(Coordinate coord : board.keySet()) {
				if(board.get(coord).getSide() == side) {
					contador++;
				}
			}
		}else {
			for(Coordinate coord : board.keySet()) {
				if(!board.get(coord).isDestroyed()) {
					contador++;
				}
			}
		}
		return contador;
	}
	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 * @throws InvalidSizeException the invalid size exception
	 */
	public Board(int size) throws InvalidSizeException {
		Objects.requireNonNull(size);
		if (size < 5) {
			throw new InvalidSizeException(size);
		}
		this.size = size;
		board = new HashMap<Coordinate, Fighter>();
	}

	/**
	 * Gets the fighter.
	 *
	 * @param f the f
	 * @return the fighter
	 * @throws FighterNotInBoardException the fighter not in board exception
	 */
	public boolean removeFighter(Fighter f) throws FighterNotInBoardException {
		Objects.requireNonNull(f);
		if (f.getPosition() != null) {
			Coordinate c = new Coordinate(f.getPosition());
			if (!Objects.isNull(board.get(c)) && board.get(c).equals(f)) {
				board.remove(f.getPosition(), f);
				return true;
			} else {
				throw new FighterNotInBoardException();
			}
		} else {
			throw new FighterNotInBoardException();
		}
	}

	/**
	 * Gets the fighter.
	 *
	 * @param c the c
	 * @return the fighter
	 */
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		Fighter f = null;
		if (board.containsKey(c)) {
			f = board.get(c).copy();
			// f = new Fighter(board.get(c));
		}
		return f;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Inside.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		/*
		 * if(board.containsKey(c)) { return true; }else { return false; }
		 */

		if ((c.getX() < size && c.getY() < size) && (c.getX() >= 0 && c.getY() >= 0)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Gets the neighborhood.
	 *
	 * @param c the c
	 * @return the neighborhood
	 * @throws OutOfBoundsException the out of bounds exception
	 */
	public TreeSet<Coordinate> getNeighborhood(Coordinate c) throws OutOfBoundsException {
		// Coordinate coord = new Coordinate(0, 0);
		Objects.requireNonNull(c);
		if (inside(c)) {
			TreeSet<Coordinate> vecinas = c.getNeighborhood();
			TreeSet<Coordinate> nuevo = new TreeSet<Coordinate>();
			for (Coordinate coordenadas : vecinas) {
				if (inside(coordenadas)) {
					nuevo.add(coordenadas);
				}
			}
			return nuevo;
		} else {
			throw new OutOfBoundsException(c);
		}

	}

	/**
	 * .
	 *
	 * @param c the c
	 * @param f the f
	 * @return the int
	 * @throws FighterAlreadyInBoardException the fighter already in board exception
	 * @throws OutOfBoundsException           the out of bounds exception
	 */
	public int launch(Coordinate c, Fighter f) throws FighterAlreadyInBoardException, OutOfBoundsException {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		int result = 0;
		if (!Objects.isNull(f.getPosition())) {
			throw new FighterAlreadyInBoardException(f.getPosition(), c);
		}
		// Si la coordenada esta en el tablero
		if (inside(c)) {
			// Si no tiene un caza asignado
			if (!board.containsKey(c)) {
				board.put(c, f);
				f.setPosition(c);
			} else {
				// Si el fighter que esta en la coordenada es del otro bando
				if (board.get(c).getSide() != f.getSide()) {
					// Se pelean
					try {
						result = f.fight(board.get(c));
					} catch (Exception FighterIsDestroyed) {
						throw new RuntimeException();
					}
					// Actualizamos los resultados de la madre
					if(result > 0) {
					f.getMotherShip().updateResults(result,f);
					board.get(c).getMotherShip().updateResults(-result, f);
					}else {
						f.getMotherShip().updateResults(result,board.get(c));
						board.get(c).getMotherShip().updateResults(-result, board.get(c));
					}
					if (result == 1) {
						// borramos al barco que hemos derrotado
						board.remove(c, f);
						// ponemos el nuevo barco
						board.put(c, f);
						f.setPosition(c);
					}
				}
			}
			return result;
		} else {
			throw new OutOfBoundsException(c);
		}
	}

	/**
	 * Patrol.
	 *
	 * @param f the f
	 */
	public void patrol(Fighter f) {
		Objects.requireNonNull(f);
		int resultado = 0;
		Set<Coordinate> vecinas;
		Fighter nuestro = board.get(f.getPosition());
		Fighter enemigo;
		if (board.containsValue(f) && !Objects.isNull(f.getPosition())) {
			try {
				vecinas = getNeighborhood(f.getPosition());
			} catch (Exception OutOfBoundsException) {
				throw new RuntimeException();
			}
			// Recorremos las vecinas
			for (Coordinate coord : vecinas) {
				enemigo = board.get(coord);
				// System.out.println("El enemigo de la coordenada " + coord + "-----");
				// Si la coordenada tiene un caza
				if (!Objects.isNull(enemigo)) {
					// System.out.println("Tiene un enemigo :0");
					// Si no son del mismo bando
					if (!enemigo.getSide().equals(nuestro.getSide())) {
						// System.out.println("No son del mismo bando: " + enemigo.getSide() +"-----"
						// +nuestro.getSide());
						// Pelean
						try {
							resultado = nuestro.fight(enemigo);
						} catch (Exception FighterIsDestroyed) {
							throw new RuntimeException();
						}
						// Actualizamos a las madres
						if(resultado >0) {
						nuestro.getMotherShip().updateResults(resultado, f);
						enemigo.getMotherShip().updateResults(-resultado, f);
						}else {
							nuestro.getMotherShip().updateResults(resultado, enemigo);
							enemigo.getMotherShip().updateResults(-resultado, enemigo);
						}
						// f ha perdido
						if (resultado == -1) {
							board.remove(f.getPosition());
							nuestro.setPosition(null);
							// System.out.println("EL PROTA HA PERDIDO");
							break;
						} else if (resultado == 1) {
							// System.out.println("EL PROTA HA GANADO");
							board.remove(enemigo.getPosition());
							enemigo.setPosition(null);
						}
					}
				}
			}
		}
	}
}
