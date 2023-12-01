package com.dao;

import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;

public class DummyDAOLayer {

    private MockDBClient dbClient;

    public DummyDAOLayer(MockDBClient dbClient) {
        this.dbClient = dbClient;
    }

    public void save(Message<Object> message) {
        String objectToSave = message.body().toString();
        Promise<String> DBPromise = this.dbClient.dummyDBSave(objectToSave);
        DBPromise.future().onComplete(responseFromDB -> message.reply("Object has been saved saved " + responseFromDB.result()));
    }
}
