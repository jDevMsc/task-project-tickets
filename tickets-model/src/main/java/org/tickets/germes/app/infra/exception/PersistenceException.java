package org.tickets.germes.app.infra.exception;

import org.tickets.germes.app.infra.exception.base.AppException;

/**
 * Data access layer unexpected situations
 */
public class PersistenceException extends AppException {

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenceException(Throwable cause) {
		super(cause);
	}

}
