package org.tickets.germes.app.infra.util;

import org.tickets.germes.app.infra.exception.flow.InvalidParameterException;

/**
 * Contains common check routines 
 */
public class Checks {
	private Checks() {		
	}
	
	/**
	 * Verifies that specified check passed and throws exception otherwise
	 */
	public static void checkParameter(boolean check, String message)
			throws InvalidParameterException {
		if (!check) {
			throw new InvalidParameterException(message);
		}
	}
}
