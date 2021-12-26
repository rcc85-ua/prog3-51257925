/*@author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model;

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
		return (Fighter) Class.forName("model.fighters." + type).getConstructor(Ship.class).newInstance(Mother);
		}catch(Exception | NoClassDefFoundError e) {
			return null;
		}
		/*Method creador = c.getDeclaredMethod(, Mother);
		*/
		
		/*switch(type) {
		case "XWing":
			return new XWing(Mother);
		case "YWing":
			return new YWing(Mother);
		case "AWing":
			return new AWing(Mother);
		case "TIEBomber":
			return new TIEBomber(Mother);
		case "TIEFighter":
			return new TIEFighter(Mother);
		case "TIEInterceptor":
			return new TIEInterceptor(Mother);
		default:
			return null;
		}
	}*/
	}
}
