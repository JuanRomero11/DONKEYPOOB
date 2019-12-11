package aplicacion;
/**
 * Clase propia de excepciones
 *
 */
public class DonkeyPoobException extends Exception {
	public static final String SIN_BARRIL = "El jugador solo tendra como obstaculo un barril.";
	public static final String SIN_SORPRESAS = "No habran sorpresas en el juego.";
	public static final String SIN_JUEGO = "Cree un juego antes de guardar.";
	public DonkeyPoobException(String message) {
		super(message);
	}
}