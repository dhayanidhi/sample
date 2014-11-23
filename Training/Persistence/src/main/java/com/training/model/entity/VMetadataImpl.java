package com.training.model.entity;

import com.training.model.api.VMetadata;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by pragati on 22.11.14.
 */
@Embeddable
public class VMetadataImpl implements VMetadata{

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "c_creation_time")
    private Calendar creationTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "c_updated_time")
    private Calendar updatedTime;

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public Calendar getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Calendar updatedTime) {
        this.updatedTime = updatedTime;
    }
}
