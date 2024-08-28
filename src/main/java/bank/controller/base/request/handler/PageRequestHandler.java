package bank.controller.base.request.handler;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public class PageRequestHandler extends BaseRequestHandler {
    @Override
    public ResponseEntity<?> handleResponse(final Object body) {
        return ResponseEntity.ok((Page) body);
    }
}
