package com.training.model.dto;

import com.training.model.api.VMetadata;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by pragati on 22.11.14.
 */
public class VNetworkDTO {

    private String uuid;

    private String locationPath;

    private String pgroupUuid;

    private Calendar createdTime;

    public VNetworkDTO(UUID uuid, UUID pgroupUuid, VMetadata vMetadata, String locationPath){
        this.uuid = uuid.toString();
        this.locationPath = locationPath;
        this.pgroupUuid = pgroupUuid.toString();
        this.createdTime = vMetadata.getCreationTime();
    }

    public String getUuid() {
        return uuid;
    }

    public String getLocationPath() {
        return locationPath;
    }

    public String getPgroupUuid() {
        return pgroupUuid;
    }

    public Calendar getCreatedTime() {
        return createdTime;
    }
}
