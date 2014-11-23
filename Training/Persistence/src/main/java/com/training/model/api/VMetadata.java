package com.training.model.api;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by pragati on 22.11.14.
 */
public interface VMetadata extends Serializable {

    Calendar getCreationTime();

    void setCreationTime(Calendar creationTime);
}
