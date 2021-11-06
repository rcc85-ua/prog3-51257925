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

/**
 * The Class Board.
 */

public class Board {

	/** The size. */
	private int size;
	private Map<Coordinate, Fighter> board;

	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 */
	public Board(int size) {
		Objects.requireNonNull(size);
		// board = new HashMap<Coordinate, Fighter>();
		this.size = size;
		board = new HashMap<Coordinate, Fighter>();
	}

	/**
	 * Gets the fighter.
	 *
	 * @param c the c
	 * @return the fighter
	 */
	public boolean removeFighter(Fighter f) {
		Objects.requireNonNull(f);
		if (f.getPosition() != null) {
			Coordinate c = new Coordinate(f.getPosition());
			if (!Objects.isNull(board.get(c)) && board.get(c).equals(f)) {
				board.remove(f.getPosition(), f);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		Fighter f = null;
		if (board.containsKey(c)) {
			f = board.get(c);
			f= new Fighter(board.get(c));
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
	 */
	public TreeSet<Coordinate> getNeighborhood(Coordinate c) {
		//Coordinate coord = new Coordinate(0, 0);
		Objects.requireNonNull(c);
		TreeSet<Coordinate> vecinas = c.getNeighborhood();
		TreeSet<Coordinate> nuevo = new TreeSet<Coordinate>();
		for(Coordinate coordenadas: vecinas) {
			if(inside(coordenadas)) {
				nuevo.add(coordenadas);
			}
		}
		return nuevo;
	}

	/**
	 * .
	 *
	 * @param c the c
	 * @param f the f
	 * @return the int
	 */
	public int launch(Coordinate c, Fighter f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		int result = 0;
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
					result = f.fight(board.get(c));
					// Actualizamos los resultados de la madre
					f.getMotherShip().updateResults(result);
					board.get(c).getMotherShip().updateResults(-result);
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
			return 0;
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
		Fighter nuestro = board.get(f.getPosition());
		Fighter enemigo;
		if (board.containsValue(f) && !Objects.isNull(f.getPosition())) {
			Set<Coordinate> vecinas = getNeighborhood(f.getPosition());
			
			//Recorremos las vecinas
			for (Coordinate coord : vecinas) {
					enemigo = board.get(coord);
					//System.out.println("El enemigo de la coordenada " + coord + "-----");
					//Si la coordenada tiene un caza
					if (!Objects.isNull(enemigo)) {
						//System.out.println("Tiene un enemigo :0");
						//Si no son del mismo bando
						if (!enemigo.getSide().equals(nuestro.getSide())) {
							//System.out.println("No son del mismo bando: " + enemigo.getSide() +"-----" +nuestro.getSide());
							//Pelean
							resultado = nuestro.fight(enemigo);
							
							//Actualizamos a las madres
							nuestro.getMotherShip().updateResults(resultado);
							enemigo.getMotherShip().updateResults(-resultado);
							
							// f ha perdido
							if (resultado == -1) {
								nuestro.setPosition(null);
								board.remove(f.getPosition());
								//System.out.println("EL PROTA HA PERDIDO");
								break;
							} else if (resultado == 1) {
								//System.out.println("EL PROTA HA GANADO");
								enemigo.setPosition(null);
								board.remove(enemigo.getPosition());
							}
						}else {
							//System.out.println("Son del mismo bando: " + enemigo.getSide() + "-----" + nuestro.getSide());
						}
					}
				}
			}
		}
}
