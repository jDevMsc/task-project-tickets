package org.tickets.germes.app.infra.exception.validation;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import org.tickets.germes.app.infra.exception.FlowException;

public class ValidationException extends FlowException {
    private static final long serialVersionUID = 6858621613562789296L;

    public <T> ValidationException(String message, Set<ConstraintViolation<T>> constraints) {
        super(message + ":" + constraints.stream().map(ConstraintViolation::getMessage).collect(
            Collectors.joining(",")));
    }
}