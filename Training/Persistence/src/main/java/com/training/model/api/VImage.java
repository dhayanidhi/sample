package com.training.model.api;

/**
 * Created by pragati on 15.11.14.
 */
public interface VImage extends Identity {

    VolumeType getImageType();

    void setImageType(VolumeType volumeType);


}
