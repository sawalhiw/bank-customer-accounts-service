package bank.controller.base;

import bank.controller.base.request.handler.ResponseHandler;
import org.springframework.http.ResponseEntity;

public class BaseController {

    public ResponseEntity<?> call(final ApiCall api) {
        final Object object = api.call();
        return ResponseHandler.getHandler(object).handleResponse(object);
    }
}
