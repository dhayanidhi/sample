package com.training.model.api;

import com.training.model.entity.VMetadataImpl;

import java.util.List;
import java.util.UUID;

/**
 * Created by pragati on 15.11.14.
 */
public interface VNetwork<T extends VMetadata> extends Identity{

    //List<VServer> getVServers();

    List<VStorage> getVStorage();

    //List<VSwitch> getVSwitch();

    UUID getPGroupId();

    void setPGroupId(UUID pGroupId);

    Location getLocation();

    void setLocation(Location location);

    T getvMetadata();

    void setvMetadata(T vMetadata);
}
