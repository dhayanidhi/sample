package com.training.model.api;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by pragati on 15.11.14.
 */
public interface Identity extends Serializable {

    UUID getUuid();

    VirtualState getVirtualState();

    String getName();

    void setUuid(UUID uuid);

    void setVirtualState(VirtualState virtualState);

    void setName(String name);
}
