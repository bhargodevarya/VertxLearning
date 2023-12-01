package com.service;

import com.dao.DummyDAOLayer;
import com.util.Constant;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;

public class DummyService {

    private DummyDAOLayer dao;

    public Future<Void> getAsyncAPIResponse(RoutingContext routingContext, Vertx vertx) {
        Promise<Void> promise = Promise.promise();
        vertx.eventBus().request(Constant.SERVICE_ADDRESS, "Message from API", response -> {
            if (response.succeeded()) {
                 routingContext.response().send(response.result().body().toString());
                 promise.complete();
            } else {
                 routingContext.response().send("Error");
                 promise.fail("Error");
            }
        });
        return promise.future();
    }
}
