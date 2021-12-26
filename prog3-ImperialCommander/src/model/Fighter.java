/**
 * @author rayane Chelihi Chelouche 
 * @author 51257925X
 */

package model;

import java.util.Objects;

import model.exceptions.FighterIsDestroyedException;


/**
 * The Class Fighter.
 */
public abstract class Fighter {

	/** The Constant DivAtk. */
	static final int DivAtk = 300;

	/** The next id. */
	private static int nextId = 1;


	/** The velocity. */
	private int velocity;

	/** The attack. */
	private int attack;

	/** The shield. */
	private int shield;

	/** The id. */
	private int id;

	/** The mother ship. */
	private Ship motherShip;

	/** The position. */
	private Coordinate position;

	/**
	 * Instantiates a new fighter.
	 *
	 * @param mother the mother
	 */
	/* TE HAS IDO A HACER LA CLASE SHIP */
	protected Fighter(Ship mother) {
		Objects.requireNonNull(mother);
		velocity = 100;
		attack = 80;
		shield = 80;
		id = nextId;
		nextId++;
		motherShip = mother;
		position = null;
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	public abstract Fighter copy();

	/**
	 * Instantiates a new fighter.
	 *
	 * @param f the f
	 */
	protected Fighter(Fighter f) {
		id = f.id;
		velocity = f.velocity; // NO SON UNA REFERENCIA
		attack = f.attack;
		shield = f.shield;
		motherShip = f.motherShip;
		position = f.position;
	}

	
	
	public int getValue() {
		return velocity+attack;
	}
	/**
	 * Gets the shield.
	 *
	 * @return the shield
	 */
	public int getShield() {
		return shield;
	}

	/**
	 * Gets the attack.
	 *
	 * @return the attack
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Gets the velocity.
	 *
	 * @return the velocity
	 */
	public int getVelocity() {
		return velocity;
	}

	/**
	 * Adds the attack.
	 *
	 * @return the mother ship
	 */
	public Ship getMotherShip() {
		return motherShip;
	}

	/**
	 * Adds the attack.
	 *
	 * @param atk the atk
	 */
	public void addAttack(int atk) {
		if (attack + atk >= 0) {
			attack += atk;
		} else {
			attack = 0;
		}
	}

	/**
	 * Adds the shield.
	 *
	 * @param sh the sh
	 */
	public void addShield(int sh) {
		shield += sh;
	}

	/**
	 * Adds the velocity.
	 *
	 * @param vl the vl
	 */
	public void addVelocity(int vl) {
		if (velocity + vl > 0) {
			velocity += vl;
		} else {
			velocity = 0;
		}
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return getClass().getSimpleName();
	}

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public abstract char getSymbol();
	/**
	 * Reset next id.
	 */
	public static void resetNextId() {
		nextId = 1;
	}

	/**
	 * Sets the position.
	 *
	 * @param c the new position
	 */
	public void setPosition(Coordinate c) {
		position = c;
	}

	/**
	 * Checks if is destroyed.
	 *
	 * @return true, if is destroyed
	 */
	public boolean isDestroyed() {
		if (shield > 0)
			return false;
		else
			return true;
	}

	/**
	 * Gets the pos.
	 *
	 * @return the pos
	 */
	public Coordinate getPosition() {
		return position;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the side.
	 *
	 * @return the side
	 */
	public Side getSide() {
		return motherShip.getSide();
	}

	/**
	 * Gets the damage.
	 *
	 * @param n     the n
	 * @param enemy the enemy
	 * @return the damage
	 */
	public int getDamage(int n, Fighter enemy) {
		Objects.requireNonNull(n);
		Objects.requireNonNull(enemy);
		return (n * attack) / DivAtk;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "(" + getType() + " " + id + " " + motherShip.getSide() + " " + position + " {" + velocity + "," + attack
				+ "," + shield + "})"; 
	}

	/**
	 * .
	 *
	 * @param enemy the enemy
	 * @return the int
	 * @throws FighterIsDestroyedException the fighter is destroyed exception
	 */
	public int fight(Fighter enemy) throws FighterIsDestroyedException{
		Objects.requireNonNull(enemy);
		int n = 0;
		int umbral;
		if (enemy.isDestroyed() || this.isDestroyed()) {
			throw new FighterIsDestroyedException();
		} else {
			while (!enemy.isDestroyed() && !this.isDestroyed()) {
				n = RandomNumber.newRandomNumber(100);
				umbral = (velocity * 100) / (this.velocity + enemy.getVelocity());
				// Si el umbral es <= que n el atacante sera caza y quita a enemigo el daño
				// hecho por ()
				if (umbral <= n) {
					enemy.shield = enemy.shield - this.getDamage(n,enemy);
					// System.out.println("El enemigo tiene " + enemy.getShield()+ " de vida");
				} else {
					this.shield = this.shield - enemy.getDamage(100-n, this);
					//System.out.println("El usuario tiene " + this.getShield()+ " de vida");
				}
				

			}
			if (shield >= 0) {
				return 1;
			} else {
				return -1;
			}

		}
	}

	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(attack, id, motherShip, position, shield, getType(), velocity);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fighter other = (Fighter) obj;
		return attack == other.attack && id == other.id && Objects.equals(motherShip, other.motherShip)
				&& Objects.equals(position, other.position) && shield == other.shield
				&& Objects.equals(getType(), other.getType()) && velocity == other.velocity;
	}
}