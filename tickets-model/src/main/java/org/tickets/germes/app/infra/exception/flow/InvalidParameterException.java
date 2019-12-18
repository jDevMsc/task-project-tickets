package org.tickets.germes.app.infra.exception.flow;

import org.tickets.germes.app.infra.exception.FlowException;

/**
 * Incorrect parameter was passed to method/constructor
 */
public class InvalidParameterException extends FlowException {


	public InvalidParameterException(String message) {
		super(message);
	}	
}
