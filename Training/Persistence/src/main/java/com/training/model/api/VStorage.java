package com.training.model.api;

/**
 * Created by pragati on 15.11.14.
 */
public interface VStorage extends Identity {

    VNetwork getVirtualNetwork();

    void setVirtualNetwork(VNetwork virtualNetwork);
}
