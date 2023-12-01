package com.dao;

import com.model.db.DBModel;
import io.vertx.core.Future;

public class DummyDAOLayer {

    private MockDBClient dbClient;

    public DummyDAOLayer(MockDBClient dbClient) {
        this.dbClient = dbClient;
    }

    public Future<DBModel> save(String objectToSave) {
        return this.dbClient.dummyDBSave(objectToSave).
                onFailure(System.out::println).
                onComplete(responseFromDB -> System.out.println(responseFromDB.result()));
    }
}
