package com;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.module.AppModule;
import io.vertx.core.Launcher;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(Arrays.asList(new AppModule()));
        Launcher.executeCommand("run", BootstrapVerticle.class.getName());
    }
}
