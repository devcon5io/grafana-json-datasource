package io.devcon5.metrics;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

/**
 *
 */
public class MongoClientProducer {

    @Inject
    private Vertx vertx;

    private MongoClient mongoClient;

    public void postInit(
            @Observes
            @Initialized(ApplicationScoped.class)
                    Object object) {

    }

    @PostConstruct
    public void init() {

        JsonObject config = new JsonObject();
        Context ctx = vertx.getOrCreateContext();

        MongoClient client = MongoClient.createShared(vertx, config);
        this.mongoClient = client;
    }

    @Produces
    @ApplicationScoped
    public MongoClient getMongoClient() {

        return mongoClient;
    }

    public void dispose(
            @Disposes
                    MongoClient client) {

        client.close();
    }
}
