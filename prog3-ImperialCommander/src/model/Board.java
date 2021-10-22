package model;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeSet;

/**
 * The Class Board.
 */
/* NO SE HA HECHO NADA, VUELVE A MIRAR LA PRACTICA MANIN */
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
		if (board.containsKey(c) && !Objects.isNull(board.get(c))) {
			return new Fighter(board.get(c));
		} else {
			return null;
		}

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
		// Objects.requireNonNull(c);
		/*
		 * if(board.containsKey(c)) { return true; }else { return false; }
		 */
		if (!Objects.isNull(c) && c.getX() < size && c.getY() < size && c.getX()>=0 && c.getY() >= 0) {
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
		Coordinate coord = new Coordinate(0,0);
		Objects.requireNonNull(c);
		TreeSet<Coordinate> vecinas = new TreeSet<Coordinate>();
		//de la posicion -1 a la posicion 1
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j < 2; j++) {
				//System.out.println(i+ " " + j);
				//Si las coordenadas son >=0
				if (c.getX() + i >= 0 && c.getY() + j >= 0) {
					//System.out.println("Son mayor que 0");
					//Si las coordenadas son menores que el tamaño del mapa
					if (c.getX() + i < size && c.getY() + i < size) {
						//System.out.println("Es menor que size");
						if (!coord.equals(new Coordinate(i,j))) {
							//System.out.println("No es la de enmedio");
							vecinas.add(new Coordinate(c.getX() + i, c.getY() + j));
					
						}
				
					}
			
				}
		
			}
		}
		return vecinas;
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
		if(inside(c)) {
			//Si no tiene un caza asignado
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
					board.remove(c,f);
					// ponemos el nuevo barco
					board.put(c, f);
					f.setPosition(c);
				}
			}
		}
		return result;
	}else {
		return 0;
	}
	}

	/**
	 * Patrol.
	 *
	 * @param f the f
	 */
	public void patrol(Fighter f) {
		if (board.containsValue(f)) {
			Coordinate position = f.getPosition();
			if (board.containsKey(position)) {
				TreeSet<Coordinate> vecinas = position.getNeighborhood();
				//por los neighborhoods
				for (Coordinate coord : vecinas) {
					//Si la coordenada tiene asignado un fighter del bando contrario
					if (board.containsKey(coord) && board.get(coord).getSide() != f.getSide()) {
						//se pega con los de la posicion
						int result = f.fight(board.get(coord));
						//System.out.println(result);
						//Si gana
						if (result == 1) {
							//Actualizamos las ganadas y perdidas
							f.getMotherShip().updateResults(result);
							board.get(coord).getMotherShip().updateResults(-result);
							//Borramos la posicion del que ha perdido
							board.get(coord).setPosition(null);
							board.put(coord,null);
							//board.put(position, f);
							//board.get(position).setPosition(position);
							}
						//Si pierde
						if (result == -1) {
							//Actualizamos ganados y perdidos
							Ship madre = f.getMotherShip();
							madre.updateResults(result);
							Ship madre2 = board.get(coord).getMotherShip();
							madre2.updateResults(-result);
							//Borramos la posicion del que ha perdido
							board.get(position).setPosition(null);
							board.put(position, null);
							//board.remove(position);
							break;
						}
					}
				}
			}
		}
	}
}
