package com;

import com.google.inject.Inject;
import io.vertx.core.AbstractVerticle;

import java.util.List;

/**
 * @author bhargodev on 01/12/23
 **/
public class VerticleRegistry {

    private List<AbstractVerticle> verticles;

    public VerticleRegistry(List<AbstractVerticle> verticles) {
        this.verticles = verticles;
    }

    public List<AbstractVerticle> getVerticles() {
        return verticles;
    }
}
