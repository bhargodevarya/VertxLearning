package com.service;

import com.dao.DummyDAOLayer;
import com.util.Constant;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class DummyResponseGenerator extends AbstractVerticle {

    private DummyDAOLayer daoLayer;

    public DummyResponseGenerator(DummyDAOLayer daoLayer) {
        this.daoLayer = daoLayer;
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        consumeEventBus(Constant.SERVICE_ADDRESS, getVertx());
    }

    public void consumeEventBus(String address, Vertx vertx) {
        vertx.eventBus().consumer(address, handler -> {
            String messageFromAPI = handler.body().toString();
            System.out.println(">>>This is from queue consumer " + messageFromAPI);
            daoLayer.save(handler);
        });
    }
}
