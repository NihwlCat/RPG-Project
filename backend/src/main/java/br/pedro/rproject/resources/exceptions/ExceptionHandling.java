package br.pedro.rproject.resources.exceptions;

import br.pedro.rproject.services.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<StandardError> handleCustomException(HttpServletRequest res, ServiceException ex) {
        StandardError error = new StandardError(
                Instant.now(),
                ex.getHttpStatus().value(),
                "Service error",
                ex.getMessage(),
                res.getRequestURI()
        );

        return ResponseEntity.status(ex.getHttpStatus()).body(error);
    }

}
