package com.training.model.api;

import java.io.Serializable;

/**
 * Created by pragati on 15.11.14.
 */
public interface LocationPricing extends Serializable {

    void setId(int id);

    int getId();

    void setValue(String value);

    String getValue();
}
