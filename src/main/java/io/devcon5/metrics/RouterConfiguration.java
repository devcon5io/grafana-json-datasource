package io.devcon5.metrics;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.jboss.resteasy.plugins.server.vertx.VertxResteasyDeployment;
import rest.x.RestxHandler;

public class RouterConfiguration {

    @Inject
    private Vertx vertx;


    @Inject
    private VertxResteasyDeployment deployment;

    @Produces
    @ApplicationScoped
    public Router getRouter(){
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route("/*").handler(RestxHandler.create(vertx, deployment));
        return router;
    }
}
