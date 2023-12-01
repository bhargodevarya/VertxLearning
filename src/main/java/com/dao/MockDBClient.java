package com.dao;

import com.model.db.DBModel;
import io.reactivex.rxjava3.core.Single;
import io.vertx.core.Future;
import io.vertx.core.Promise;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MockDBClient {

    public Future<DBModel> dummyDBSave(String someRandomID) {
        Promise<DBModel> promise = Promise.promise();

        Single.timer(1500L, TimeUnit.MILLISECONDS).map(value -> new DBModel(UUID.randomUUID().toString().concat(" ".concat(someRandomID))))
                .subscribe(promise::complete, error -> promise.fail("Could not fetch value"));
        return promise.future();
    }
}
