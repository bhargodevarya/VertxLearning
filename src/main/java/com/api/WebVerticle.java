package com.api;

import com.service.DummyService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;

public class WebVerticle extends AbstractVerticle {

    private DummyService dummyService;

    public WebVerticle(DummyService dummyService) {
        this.dummyService = dummyService;
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Router router = Router.router(this.getVertx());
        router.get("/demo").handler(routingContext -> {
           routingContext.pathParams().entrySet().forEach(System.out::println);
           dummyService.getAsyncAPIResponse(routingContext, this.getVertx());
        });
        this.getVertx().createHttpServer().requestHandler(router).listen(8081)
                .onSuccess(handler -> System.out.println("Server started on " + handler.actualPort()));
    }
}
