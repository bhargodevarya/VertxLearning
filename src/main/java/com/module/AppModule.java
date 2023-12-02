package com.module;

import com.VerticleRegistry;
import com.api.WebVerticle;
import com.dao.DummyDAOLayer;
import com.dao.MockDBClient;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.kafka.KafkaMessenger;
import com.service.DummyResponseGenerator;
import com.service.DummyService;
import io.vertx.core.AbstractVerticle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author bhargodev on 01/12/23
 **/
public class AppModule extends AbstractModule {

    @Provides
    static DummyDAOLayer provideDAOLayer(MockDBClient mockDBClient) {
        return new DummyDAOLayer(mockDBClient);
    }

    @Provides
    static MockDBClient provideMockDBClient() {
        return new MockDBClient();
    }


    @Provides
    static DummyService provideDummyService(DummyDAOLayer daoLayer) {
        return new DummyService(daoLayer);
    }

    @Provides
    static DummyResponseGenerator provideDummyResponseGenerator(DummyDAOLayer daoLayer) {
        return new DummyResponseGenerator(daoLayer);
    }

    @Provides
    static WebVerticle provideWebVerticle(DummyService service) {
        return new WebVerticle(service);
    }

    @Provides
    static List<AbstractVerticle> provideVerticles(WebVerticle webVerticle, DummyResponseGenerator dummyResponseGenerator) {
        return Arrays.asList(webVerticle, dummyResponseGenerator);
    }

    @Provides
    static AbstractVerticle provideKafkaMessengerVerticle() {
        return new KafkaMessenger();
    }

    @Provides
    static VerticleRegistry provideVerticleRegistry(WebVerticle webVerticle, DummyResponseGenerator dummyResponseGenerator, KafkaMessenger kafkaMessenger) {
        return new VerticleRegistry(Arrays.asList(webVerticle, dummyResponseGenerator, kafkaMessenger));
    }
}
