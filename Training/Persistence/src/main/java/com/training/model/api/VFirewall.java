package com.training.model.api;

/**
 * Created by pragati on 15.11.14.
 */
public interface VFirewall extends Identity{

    VNic getVNic();

    void setNic(VNic vNic);

}
