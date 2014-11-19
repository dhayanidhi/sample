package com.training.client;

import com.google.gson.*;
import com.training.model.api.VNetwork;

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
}
