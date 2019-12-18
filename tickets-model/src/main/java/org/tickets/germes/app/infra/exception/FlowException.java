package org.tickets.germes.app.infra.exception;

import org.tickets.germes.app.infra.exception.base.AppException;

/**
 * Signals about exceptional cases in the application logic
 */
public class FlowException extends AppException {

	public FlowException(String message, Throwable cause) {
		super(message, cause);
	}

	public FlowException(String message) {
		super(message);
	}
}
