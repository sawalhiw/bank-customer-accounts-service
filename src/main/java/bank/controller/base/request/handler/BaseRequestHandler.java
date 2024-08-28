package bank.controller.base.request.handler;

import org.springframework.http.ResponseEntity;

public abstract class BaseRequestHandler {

    public abstract ResponseEntity<?> handleResponse(final Object body);
}
