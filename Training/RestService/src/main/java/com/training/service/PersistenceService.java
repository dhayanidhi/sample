package com.training.service;

import com.google.common.base.Stopwatch;
import com.training.access.TrainingAccessImpl;
import com.training.api.LockTraining;
import com.training.api.TrainingAccess;
import com.training.client.JsonConverterUtil;
import com.training.model.api.VNetwork;
import com.training.model.dto.VNetworkDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by pragati on 15.11.14.
 */
@Stateless
@Path("/persist")
public class PersistenceService {


    @EJB(name = "TrainingAccess")
    TrainingAccess trainingAccess;

    @EJB(name = "lockTraining")
    LockTraining lockTraining;

    @GET
    @Path("/{param}")
    @Produces({MediaType.TEXT_PLAIN})
    public Response test(@PathParam("param")String name){
        return Response.ok().entity(trainingAccess.getValue(name)).build();
    }

    @POST
    @Path("/cluster/{param}")
    @Produces({MediaType.TEXT_PLAIN})
    public Response createCluster(@PathParam("param")String location){
        return Response.ok().entity(trainingAccess.insertCluster(location)).build();
    }

    @POST
    @Path("/network/{param}")
    @Produces({MediaType.TEXT_PLAIN})
    public Response createNetwork(@PathParam("param")String cluster){
        return Response.ok().entity(trainingAccess.insertNetwork(cluster)).build();
    }

    @GET
    @Path("/network/{param}")
    @Produces({MediaType.TEXT_PLAIN})
    public Response getNetwork(@PathParam("param")String network){
        VNetworkDTO vNetwork = trainingAccess.getMetaInfo(network);
        return Response.ok().entity(JsonConverterUtil.getJsonNetworkMetaInfo(vNetwork)).build();
    }

    @GET
    @Path("/network")
    @Produces({MediaType.TEXT_PLAIN})
    public Response getNetwork(){
        List<VNetworkDTO> vNetwork = trainingAccess.getVNetworkMetaInfos();
        return Response.ok().entity(JsonConverterUtil.getJsonNetworkMetaInfo(vNetwork)).build();
    }

    @GET
    @Path("/networkMeta/{param}")
    @Produces({MediaType.TEXT_PLAIN})
    public Response getNetworkMeta(@PathParam("param")String network){
        VNetwork vNetwork = lockTraining.getNetwork(network);
        return Response.ok().entity(JsonConverterUtil.getJsonNetworkMetaInfo(vNetwork)).build();
    }

    @POST
    @Path("/networkMeta/{param}")
    @Produces({MediaType.TEXT_PLAIN})
    public Response setLock(@PathParam("param")String network) throws Throwable{
        Stopwatch stopwatch = Stopwatch.createStarted();
        lockTraining.lockNetwork(network);
        stopwatch.stop();
        return Response.ok().entity("Holded lock for " + stopwatch.elapsed(TimeUnit.SECONDS)).build();
    }
}
