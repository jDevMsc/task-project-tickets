package org.tickets.germes.app.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Global Jersey handler that catches any Exception-based errors
 *
 */
@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	private final Response SERVER_ERROR;

	public GlobalExceptionHandler() {
		SERVER_ERROR = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	//log exception and return code

	@Override
	public Response toResponse(Exception ex) {
		LOGGER.error(ex.getMessage(), ex);

		return SERVER_ERROR;
	}
}
