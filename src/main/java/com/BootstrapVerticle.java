package com;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.module.AppModule;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;

import java.util.Arrays;

/**
 * @author bhargodev on 01/12/23
 **/
public class BootstrapVerticle extends AbstractVerticle {

    @Override
    public void init(Vertx vertx, Context context) {
        Injector injector = Guice.createInjector(Arrays.asList(new AppModule()));
        injector.getInstance(VerticleRegistry.class).getVerticles().forEach(vertx::deployVerticle);
    }
}
