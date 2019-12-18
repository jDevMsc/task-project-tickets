package org.tickets.germes.app.infra.exception;

import org.tickets.germes.app.infra.exception.base.AppException;

/**
 * Exception cases in the work of external services and API
 *
 */
public class CommunicationException extends AppException {

	public CommunicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommunicationException(String message) {
		super(message);
	}

}
