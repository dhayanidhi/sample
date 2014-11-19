package com.training.model.api;

import java.util.List;

/**
 * Created by pragati on 15.11.14.
 */
public interface VServer extends Identity{

    List<VVirtualImageMount> getVirtualImageMount();

    List<VVirtualMount> getVirtualMount();

    List<VNic> getVirtualNics();

    void setVirtualImageMount(List<VVirtualImageMount> vVirtualImageMounts);

    void setVirtualMount(List<VVirtualMount> vVirtualMounts);

    void setVirtualNics(List<VNic> vNics);

    VNetwork getVirtualNetwork();

    void setVirtualNetwork(VNetwork virtualNetwork);

}
