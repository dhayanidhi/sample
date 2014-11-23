package com.training.api;

import com.training.model.api.VNetwork;
import com.training.model.dto.VNetworkDTO;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

/**
 * Created by pragati on 16.11.14.
 */
@Remote
public interface TrainingAccess extends Serializable {

    public String insertNetwork(String cluster);

    public String insertCluster(String location);

    public String getValue(String val);

    public VNetworkDTO getMetaInfo(String networkUuid);

    public List<VNetworkDTO> getVNetworkMetaInfos();

}
