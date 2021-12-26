/* 
 * @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * The Class YWing.
 */
public class YWing extends Fighter {
	
	/** The Constant DivAtk. */
	static final int DivAtk=300;
	
	/**
	 * Instantiates a new y wing.
	 *
	 * @param mother the mother
	 */
	public YWing(Ship mother) {
		super(mother);
		addVelocity(-20);
		addAttack(-10);
		addShield(+30);
	}
	
	/**
	 * Instantiates a new y wing.
	 *
	 * @param f the f
	 */
	private YWing(Fighter f) {
		super(f);
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new YWing(this);
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'Y';
	}
	
	/**
	 * Gets the damage.
	 *
	 * @param n the n
	 * @param enemy the enemy
	 * @return the damage
	 */
	@Override
	public int getDamage(int n, Fighter enemy) {
		switch(enemy.getSymbol()) {
		case 'f':
		case 'i':
			return ((n * getAttack()) / DivAtk)/3;
		case 'b': 
			return ((n * getAttack()) / DivAtk)/2;
		default:
			return (n * getAttack()) / DivAtk;
		}
	}
}
