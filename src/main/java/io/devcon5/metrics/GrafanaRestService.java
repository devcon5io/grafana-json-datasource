package io.devcon5.metrics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 */
@Path("grafana")
public class GrafanaRestService {

    @GET
    @Path("timeseries")
    public String getInfo(){
        return "info";
    }
}
