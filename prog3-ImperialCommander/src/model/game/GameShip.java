/*
 * @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.game;

import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;
import model.game.exceptions.*;
import model.*;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Class GameShip.
 */
public class GameShip extends Ship {
	
	/** The Wins score. */
	private WinsScore WinsScore;
	
	/** The Destroyed fighters score. */
	private DestroyedFightersScore DestroyedFightersScore;
	/**
	 * Instantiates a new game ship.
	 *
	 * @param name the name
	 * @param side the side
	 */
	public GameShip(String name, Side side) {
		super(name, side);
	}

	/**
	 * Checks if is fleet destroyed.
	 *
	 * @return true, if is fleet destroyed
	 */
	
	public boolean isFleetDestroyed() {

		for (int i = 0; i < fleet.size(); i++) {
			if (!fleet.get(i).isDestroyed()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the fighter.
	 *
	 * @param id the id
	 * @return the fighter
	 * @throws WrongFighterIdException the wrong fighter id exception
	 */
	private Fighter getFighter(int id) throws WrongFighterIdException {
		for (int i = 0; i < fleet.size(); i++) {
			if (fleet.get(i).getId() == id) {
				if (fleet.get(i).isDestroyed()) {
					throw new WrongFighterIdException(id);
				} else {
					return fleet.get(i);
				}
			}
		}
		throw new WrongFighterIdException(id);
	}

	/**
	 * Gets the fighters id.
	 *
	 * @param where the where
	 * @return the fighters id
	 */
	public List<Integer> getFightersId(String where) {
		List<Integer> resultado = new ArrayList<Integer>();
		/*
		 * Que no esten en el tablero
		 *
		 */
		if (!where.equals("board")) {
			for (int i = 0; i < fleet.size(); i++) {
				if (!fleet.get(i).isDestroyed()) {
					resultado.add(fleet.get(i).getId());
				}

			}
		} else {
			for (int i = 0; i < fleet.size(); i++) {
				if (!fleet.get(i).isDestroyed()) {
					if (fleet.get(i).getPosition() != null) {
						resultado.add(fleet.get(i).getId());
					}
				}
			}

		}
		return resultado;
	}

	/**
	 * Launch.
	 *
	 * @param id the id
	 * @param c the c
	 * @param b the b
	 * @throws WrongFighterIdException the wrong fighter id exception
	 * @throws FighterAlreadyInBoardException the fighter already in board exception
	 * @throws OutOfBoundsException the out of bounds exception
	 */
	public void launch(int id, Coordinate c, Board b) throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException {
		Objects.requireNonNull(id);
		Objects.requireNonNull(c);
		Objects.requireNonNull(b);
		Fighter caza = getFighter(id);
		b.launch(c, caza);
	}
	
	/**
	 * Patrol.
	 *
	 * @param id the id
	 * @param b the b
	 * @throws WrongFighterIdException the wrong fighter id exception
	 * @throws FighterNotInBoardException the fighter not in board exception
	 */
	public void patrol(int id, Board b) throws WrongFighterIdException, FighterNotInBoardException {
		Objects.requireNonNull(id);
		Objects.requireNonNull(b);
		Fighter caza = null;
		caza = getFighter(id);
		if(caza.getPosition() == null) {
			throw new FighterNotInBoardException();
		}
		b.patrol(caza);
	}

	
	/**
	 * Improve fighter.
	 *
	 * @param id the id
	 * @param qty the qty
	 * @param b the b
	 * @throws WrongFighterIdException the wrong fighter id exception
	 */
	public void improveFighter(int id, int qty, Board b) throws WrongFighterIdException {
		Objects.requireNonNull(id);
		Objects.requireNonNull(qty);
		Objects.requireNonNull(b);
		Fighter caza;
			caza = getFighter(id);
		try {
			b.removeFighter(caza);
		}catch(Exception FighterNotInBoardException){
		}
		caza.addAttack(qty/2); //97/2 = 48 //85 = 133
		caza.addShield(qty/2+qty%2);
		}
	
	/**
	 * Update results.
	 *
	 * @param r the r
	 * @param luchador the luchador
	 */
	@Override
	public void updateResults(int r, Fighter luchador) {
		super.updateResults(r, luchador);
		if(r == 1) {
			WinsScore.score(1);
			DestroyedFightersScore.score(luchador);
		}
	}
	
	
	/**
	 * Gets the wins score.
	 *
	 * @return the wins score
	 */
	public WinsScore getWinsScore() {
		return WinsScore;
	}

	/**
	 * Sets the wins score.
	 *
	 * @param winsScore the new wins score
	 */
	public void setWinsScore(WinsScore winsScore) {
		WinsScore = winsScore;
	}

	/**
	 * Gets the destroyed fighters score.
	 *
	 * @return the destroyed fighters score
	 */
	public DestroyedFightersScore getDestroyedFightersScore() {
		return DestroyedFightersScore;
	}

	/**
	 * Sets the destroyed fighters score.
	 *
	 * @param destroyedFightersScore the new destroyed fighters score
	 */
	public void setDestroyedFightersScore(DestroyedFightersScore destroyedFightersScore) {
		DestroyedFightersScore = destroyedFightersScore;
	}
}