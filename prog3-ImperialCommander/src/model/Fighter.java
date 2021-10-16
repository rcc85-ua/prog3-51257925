/**
 * @author rayane Chelihi Chelouche 
 * @author 51257925X
 */
package model;


/**
 * The Class Fighter.
 */
public class Fighter {
	
	/** The Constant DivAtk. */
	static final int DivAtk = 300;
	
	/** The next id. */
	private static int nextId = 1;
	
	/** The type. */
	private String type;
	
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
	 * @param type the type
	 * @param mother the mother
	 */
	/* TE HAS IDO A HACER LA CLASE SHIP*/
	Fighter(String type, Ship mother) {
		velocity = 100;
		attack = 80;
		shield = 80;
		this.type = type;
		id= nextId;
		nextId++;
		motherShip = mother;
		position = null;
	}
	
	/**
	 * Instantiates a new fighter.
	 *
	 * @param f the f
	 */
	public Fighter(Fighter f) {
		id = f.id;
		velocity = f.velocity; //NO SON UNA REFERENCIA
		attack = f.attack;
		shield = f.shield;
		type = f.type;
		motherShip = f.motherShip;
		position = f.position;
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
	 * @param atk the atk
	 */
	public Ship getMotherShip() {
		return motherShip;
	}
	public void addAttack(int atk) {
		if(attack+atk > 0) {
			attack+=atk;
		}else {
			attack = 0;
		}
	}
	
	/**
	 * Adds the shield.
	 *
	 * @param sh the sh
	 */
	public void addShield(int sh){
		if(shield+sh > 0) {
			shield+=sh;
		}else {
			shield = 0;
		}
	}
	
	/**
	 * Adds the velocity.
	 *
	 * @param vl the vl
	 */
	public void addVelocity(int vl) {
		if(velocity + vl > 0) {
			velocity+=vl;
		}else {
			velocity = 0;
		}
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Reset next id.
	 */
	public static  void resetNextId() {
		nextId = 1;
	}
	
	public void setPosition(Coordinate c) {
		position = c;
	}
	/**
	 * Checks if is destroyed.
	 *
	 * @return true, if is destroyed
	 */
	public boolean isDestroyed() {
		if(shield >0)
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
	 * @param n the n
	 * @param enemy the enemy
	 * @return the damage
	 */
	public int getDamage(int n, Fighter enemy) {
		return (n*this.attack)/DivAtk;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "(" + type + " "+ id+ " " + motherShip.getSide() +" "+ position + " {" + velocity + "," + attack
		+ "," + shield + "})";
	}
	
	/**
	 * Fight.
	 *
	 * @param enemy the enemy
	 * @return the int
	 */
	public int fight(Fighter enemy) {
		int n;
		int umbral;
		if(enemy.isDestroyed() || this.isDestroyed()) {
			return 0;
		}
		do {
		n=RandomNumber.newRandomNumber(100);
		//Umbral = (velocity*100)/(velocity1+velocity2)
		umbral = (velocity*100)/(this.velocity+enemy.velocity);
		//Si el umbral es <= que n el atacante sera caza y quita a enemigo el daño hecho por getDamage()
		if(umbral <= n) {
			enemy.shield=enemy.shield - this.getDamage(n,enemy);
		}else {
			this.shield = this.shield - enemy.getDamage(100-n, this);
		}
		//Si el umbral es > que n el atacante sera enemigo y quita a caza hecho por getDamage(100-n,)
	
		}while(enemy.shield > 0 || shield > 0);
		if(shield >=0) {
			return 1;
		}else {
			return -1;
		}

	}
}