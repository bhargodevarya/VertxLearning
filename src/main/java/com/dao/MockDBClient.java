package com.dao;

import io.reactivex.rxjava3.core.Single;
import io.vertx.core.Promise;

import java.util.concurrent.TimeUnit;

public class MockDBClient {

    public Promise<String> dummyDBSave(String someRandomID) {
        Promise<String> promise = Promise.promise();

        Single.timer(1500L, TimeUnit.MILLISECONDS).map(Object::toString)
                .subscribe(promise::complete, error -> promise.fail("Could not fetch value"));
        return promise;
    }
}
