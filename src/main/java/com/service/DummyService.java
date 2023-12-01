package com.service;

import com.dao.DummyDAOLayer;
import com.util.Constant;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;


public class DummyService {

    private DummyDAOLayer dao;

    public DummyService(DummyDAOLayer dao) {
        this.dao = dao;
    }

    /**
     * Gets the API response by sending the message over the {@link io.vertx.core.eventbus.EventBus}
     *
     * @param routingContext
     * @param vertx
     * @return
     */
    public Future<Void> getAsyncAPIResponseWithEB(RoutingContext routingContext, Vertx vertx) {
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

    /**
     * Gets the API response without sending the message over the {@link io.vertx.core.eventbus.EventBus}
     *
     * @param routingContext
     * @param vertx
     */
    public void getASyncAPIResponseWithoutEB(RoutingContext routingContext, Vertx vertx) {
        this.dao.save("Message from API").onSuccess(handler -> {
            routingContext.response().send(handler.getValue());
        });
    }
}
