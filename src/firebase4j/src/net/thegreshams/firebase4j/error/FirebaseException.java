package firebase4j.src.net.thegreshams.firebase4j.error;

import org.apache.log4j.Logger;

/**
 * Added by Emily Huskins 11/2/2016
 * Created by Brandon Gresham (bane73)
 * Original source found on GitHub at:https://github.com/bane73/firebase4j
 */
public class FirebaseException extends Throwable {

	protected static final Logger LOGGER = Logger.getRootLogger();
	
	private static final long serialVersionUID = 1L;

	public FirebaseException( String message ) {
		super( message );
	}
	
	public FirebaseException( String message, Throwable cause ) {
		super( message, cause );
	}
	
}
