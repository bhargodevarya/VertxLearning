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

    /**
     * Consumes messages from a given address and invokes the DAO layer
     *
     * Not passing the EventBus Message to DAO layer to avoid coupling.
     * Unless there is a specific reason, the {@link io.vertx.core.eventbus.Message} should not be passed to layer beyond the consumer
     * @param address to consume from
     * @param vertx {@link io.vertx.core.Vertx}
     */
    public void consumeEventBus(String address, Vertx vertx) {
        vertx.eventBus().consumer(address, message -> {
            String messageFromAPI = message.body().toString();
            daoLayer.save(messageFromAPI).onSuccess(successDBModel -> message.reply(successDBModel.getValue()));
        });
    }
}
