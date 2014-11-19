package com.training.model.api;

import java.io.Serializable;

/**
 * Created by pragati on 15.11.14.
 */
public interface Location extends Serializable {

    int getId();

    void setId(int id);

    String getPath();

    void setPath(String path);

}
