package com.training.api;

import com.training.model.api.VNetwork;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.io.Serializable;

/**
 * Created by pragati on 16.11.14.
 */
@Remote
public interface TrainingAccess extends Serializable {

    public String insertNetwork(String cluster);

    public String insertCluster(String location);

    public String getValue(String val);

    public VNetwork getMetaInfo(String networkUuid);

}
