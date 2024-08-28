package bank.controller.base.request.handler;

import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CollectionRequestHandler extends BaseRequestHandler {
    @Override
    public ResponseEntity<?> handleResponse(final Object body) {
        return ResponseEntity.ok(new PageImpl<>((List<? extends Object>) body));
    }
}
