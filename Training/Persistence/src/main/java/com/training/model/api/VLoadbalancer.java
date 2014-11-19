package com.training.model.api;

import java.util.List;

/**
 * Created by pragati on 15.11.14.
 */
public interface VLoadbalancer extends Identity {

    VNetwork getVNetwork();

    List<VNic> getVNic();

    void setVNetwork(VNetwork vNetwork);

    void setNic(List<VNic> vNics);
}
