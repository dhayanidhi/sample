package com.training.model.api;

import java.util.List;

/**
 * Created by pragati on 15.11.14.
 */
public interface VSwitch extends Identity {

    VNetwork getVirtualNetwork();

    void setVirtualNetwork(VNetwork virtualNetwork);

    List<VNic> getVNics();

    void setVNics();
}
