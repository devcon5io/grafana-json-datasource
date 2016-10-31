package io.devcon5.metrics;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 */
@Path("metrics")
public class MetricsResource {

    @GET
    @Produces("application/json")
    @Path("/")
    public String get() {

        return "Metrics Service";
    }

    @OPTIONS
    @Path("/annotations")
    public Response annotationOptions() {

        return Response.noContent()
                       .header("Access-Control-Allow-Headers", "accept, content-type")
                       .header("Access-Control-Allow-Methods", "POST")
                       .header("Access-Control-Allow-Origin", "*")
                       .build();
    }

    @POST
    @Path("/annotations")
    @Produces("application/json")
    @Consumes("application/json")
    public JsonArray annotation(JsonObject annotation) {

        JsonArrayBuilder arr = Json.createArrayBuilder();
        JsonObjectBuilder obj = Json.createObjectBuilder();
        JsonObject an = annotation.getJsonObject("annotation");
        obj.add("annotation", an);

        Range range = Range.from(annotation.getJsonObject("range"));

        obj.add("time", (range.getDuration() / 2) + range.getStart());
        obj.add("title", "TestTitle");
        obj.add("tags", Json.createArrayBuilder().add("tag1").add("tag2").add("tag3").build());
        obj.add("text", "someText");

        arr.add(obj);
        return arr.build();
    }

    @OPTIONS
    @Path("/search")
    public Response searchOptions() {

        return Response.noContent()
                       .header("Access-Control-Allow-Headers", "accept, content-type")
                       .header("Access-Control-Allow-Methods", "POST")
                       .header("Access-Control-Allow-Origin", "*")
                       .build();
    }

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public JsonArray search(JsonObject query) {

        String sq = query.toString();
        System.out.println(sq);

        JsonArrayBuilder arr = Json.createArrayBuilder();
        arr.add("upper_75");
        arr.add("upper_80");
        arr.add("upper_90");
        return arr.build();
    }

    @OPTIONS
    @Path("/query")
    public Response queryOptions() {

        return Response.noContent()
                       .header("Access-Control-Allow-Headers", "accept, content-type")
                       .header("Access-Control-Allow-Methods", "POST")
                       .header("Access-Control-Allow-Origin", "*")
                       .build();
    }

    @POST
    @Path("/query")
    @Consumes("application/json")
    @Produces("application/json")
    public JsonArray query(JsonObject query) {

        JsonArrayBuilder arr = Json.createArrayBuilder();

        String sq = query.toString();
        System.out.println(sq);

        JsonArray targets = query.getJsonArray("targets");
        if (targets != null) {
            JsonObjectBuilder target = Json.createObjectBuilder();
            String tgt = targets.getJsonObject(0).getString("target");
            if (tgt != null) {
                target.add("target", tgt == null ? "" : tgt);
            }
            Range range = Range.from(query.getJsonObject("range"));
            target.add("datapoints", createDatapoints(range));
            arr.add(target);
        }

        return arr.build();
    }

    private JsonArrayBuilder createDatapoints(Range range) {

        JsonArrayBuilder datapoints = Json.createArrayBuilder();

        int samples = 100;
        long step = (range.getDuration()) / samples;

        for (int i = 0; i < samples; i++) {
            datapoints.add(Json.createArrayBuilder().add(Math.random() + i).add(range.getStart() + i * step));
        }
        return datapoints;
    }
}
