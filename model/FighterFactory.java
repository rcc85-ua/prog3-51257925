/*@author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model;

import java.lang.reflect.Constructor;
import java.util.Objects;


/**
 * A factory for creating Fighter objects.
 */
public class FighterFactory {
	
	/**
	 * Creates a new Fighter object.
	 *
	 * @param type the type
	 * @param Mother the mother
	 * @return the fighter
	 */
	public static Fighter createFighter(String type, Ship Mother){
		Objects.requireNonNull(type);
		Objects.requireNonNull(Mother);
		try {
			Class<?> newFighter = Class.forName("model.fighters." + type);
			Constructor<?> c = newFighter.getDeclaredConstructor(Ship.class);
			return (Fighter) c.newInstance(Mother);
		//return (Fighter) Class.forName("model.fighters." + type).getConstructor(Ship.class).newInstance(Mother);
		}catch(Exception | NoClassDefFoundError e) {
			return null;
		}
	}
}
