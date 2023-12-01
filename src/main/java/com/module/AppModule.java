package com.module;

import com.api.WebVerticle;
import com.dao.DummyDAOLayer;
import com.dao.MockDBClient;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.service.DummyResponseGenerator;
import com.service.DummyService;

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

}
