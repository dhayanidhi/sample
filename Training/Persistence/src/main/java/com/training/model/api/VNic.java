package com.training.model.api;

import java.util.List;

/**
 * Created by pragati on 15.11.14.
 */
public interface VNic extends Identity {

    Identity getHost();

    void setHost(Identity identity);

    VSwitch getSwitch();

    void setSwitch(VSwitch vSwitch);

    List<VFirewall> getFirewall();

    void setFirewall(List<VFirewall> vFirewalls);


}
