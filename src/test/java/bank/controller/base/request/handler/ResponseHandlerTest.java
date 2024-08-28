package bank.controller.base.request.handler;


import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ResponseHandlerTest {

    @Test
    public void testGetHandlerWithCollection() {
        List<String> collection = new ArrayList<>();
        BaseRequestHandler handler = ResponseHandler.getHandler(collection);
        assertInstanceOf(CollectionRequestHandler.class, handler);
    }

    @Test
    public void testGetHandlerWithPage() {
        Page<String> page = new PageImpl<>(new ArrayList<>());
        BaseRequestHandler handler = ResponseHandler.getHandler(page);
        assertInstanceOf(PageRequestHandler.class, handler);
    }

    @Test
    public void testGetHandlerWithOtherObject() {
        String body = "test";
        BaseRequestHandler handler = ResponseHandler.getHandler(body);
        assertInstanceOf(DefaultRequestHandler.class, handler);
    }
}