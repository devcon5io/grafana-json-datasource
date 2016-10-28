package io.devcon5;

import static org.junit.Assert.assertNotNull;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import org.jboss.resteasy.plugins.server.vertx.VertxResteasyDeployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RouterConfigurationTest {

    @Mock
    private Vertx vertx;

    @Mock
    private VertxResteasyDeployment deployment;

    /**
     * The class under test
     */
    @InjectMocks
    private RouterConfiguration subject;


    @Test
    public void getRouter() throws Exception {
        Router router = subject.getRouter();

        assertNotNull(router);
        Route route = router.get("/*");
        assertNotNull(route);
    }

}
