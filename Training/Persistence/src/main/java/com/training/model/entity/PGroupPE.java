package com.training.model.entity;

import com.training.model.api.Location;
import com.training.model.api.PGroup;

import javax.persistence.*;

/**
 * Created by pragati on 15.11.14.
 */
@Entity
@Table(name = "test_pgroup")
public class PGroupPE extends AbstractIdentity implements PGroup {

    @ManyToOne
    @JoinColumn(name = "fk_location")
    private VLocationPE location;

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = (VLocationPE)location;
    }
}
