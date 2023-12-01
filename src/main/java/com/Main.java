package com;

import com.api.WebVerticle;
import com.dao.DummyDAOLayer;
import com.dao.MockDBClient;
import com.google.common.collect.Iterables;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.module.AppModule;
import com.service.DummyResponseGenerator;
import com.service.DummyService;
import io.vertx.core.Vertx;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(Arrays.asList(new AppModule()));
        DummyResponseGenerator responseGenerator = injector.getInstance(DummyResponseGenerator.class);
        WebVerticle webVerticle = injector.getInstance(WebVerticle.class);
        Arrays.asList(responseGenerator, webVerticle).forEach(Vertx.vertx()::deployVerticle);
    }
}
