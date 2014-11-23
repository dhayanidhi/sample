package com.training.client;

import com.google.gson.*;
import com.training.model.api.VNetwork;
import com.training.model.dto.VNetworkDTO;

import java.util.List;

/**
 * Created by pragati on 16.11.14.
 */
public class JsonConverterUtil {

    public static String getJsonNetworkMetaInfo(VNetwork vNetwork){
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        JsonObject network = new JsonObject();
        network.addProperty("networkId", vNetwork.getUuid().toString());

        JsonArray meta = new JsonArray();
        JsonObject dataset = new JsonObject();
        dataset.addProperty("location", vNetwork.getLocation().getPath());
        dataset.addProperty("networkName", vNetwork.getName());
        dataset.addProperty("clusterName", vNetwork.getPGroupId().toString());
        meta.add(dataset);

        network.add("meta", meta);
        return gson.toJson(network);
    }

    public static String getJsonNetworkMetaInfo(VNetworkDTO vNetwork){
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        JsonObject network = new JsonObject();
        network.addProperty("networkId", vNetwork.getUuid().toString());

        JsonArray meta = new JsonArray();
        JsonObject dataset = new JsonObject();
        dataset.addProperty("location", vNetwork.getLocationPath());
        dataset.addProperty("networkName", vNetwork.getUuid());
        dataset.addProperty("clusterName", vNetwork.getPgroupUuid());
        dataset.addProperty("createdTime", vNetwork.getCreatedTime().getTimeInMillis());
        meta.add(dataset);

        network.add("meta", meta);
        return gson.toJson(network);
    }

    public static String getJsonNetworkMetaInfo(List<VNetworkDTO> vNetworks){
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        JsonObject result = new JsonObject();
        JsonArray collection = new JsonArray();
        for(VNetworkDTO vNetwork : vNetworks) {
            JsonObject network = new JsonObject();
            network.addProperty("networkId", vNetwork.getUuid().toString());

            JsonArray meta = new JsonArray();
            JsonObject dataset = new JsonObject();
            dataset.addProperty("location", vNetwork.getLocationPath());
            dataset.addProperty("networkName", vNetwork.getUuid());
            dataset.addProperty("clusterName", vNetwork.getPgroupUuid());
            dataset.addProperty("createdTime", vNetwork.getCreatedTime().getTimeInMillis());
            meta.add(dataset);
            network.add("meta", meta);
            collection.add(network);
        }
        result.add("network", collection);
        return gson.toJson(result);
    }
}
