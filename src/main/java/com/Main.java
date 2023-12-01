package com;

import com.api.WebVerticle;
import com.dao.DummyDAOLayer;
import com.dao.MockDBClient;
import com.service.DummyResponseGenerator;
import com.service.DummyService;
import io.vertx.core.Vertx;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        MockDBClient mockDBClient = new MockDBClient();
        DummyDAOLayer dummyDAOLayer = new DummyDAOLayer(mockDBClient);
        DummyService dummyService = new DummyService(dummyDAOLayer);
        DummyResponseGenerator responseGenerator = new DummyResponseGenerator(dummyDAOLayer);
        WebVerticle webVerticle = new WebVerticle(dummyService);
        Arrays.asList(responseGenerator, webVerticle).forEach(Vertx.vertx()::deployVerticle);
    }
}
