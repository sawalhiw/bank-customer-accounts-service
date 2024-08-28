package bank.controller.base.request.handler;

import org.springframework.http.ResponseEntity;

public class DefaultRequestHandler extends BaseRequestHandler {
    @Override
    public ResponseEntity<?> handleResponse(final Object body) {
        return ResponseEntity.ok(body);
    }
}
