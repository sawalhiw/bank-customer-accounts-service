package bank.controller.base.request.handler;

import org.springframework.data.domain.Page;

import java.util.Collection;

public class ResponseHandler {

    public static BaseRequestHandler getHandler(final Object body) {
        if (body instanceof Collection) {
            return new CollectionRequestHandler();
        }
        if (body instanceof Page) {
            return new PageRequestHandler();
        }
        return new DefaultRequestHandler();
    }
}
