package com.training.api;

import com.training.model.entity.VNetworkPE;

import javax.ejb.Remote;
import java.io.Serializable;

/**
 * Created by pragati on 19.11.14.
 */
@Remote
public interface LockTraining extends Serializable {

    public void lockNetwork(String networkUuid) throws Throwable;

    public VNetworkPE getNetwork(String networkUuid);
}
