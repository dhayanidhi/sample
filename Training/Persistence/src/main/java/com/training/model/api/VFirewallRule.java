package com.training.model.api;

/**
 * Created by pragati on 15.11.14.
 */
public interface VFirewallRule extends Identity {

    VFirewall getFirewall();

    int getPort();

    String getHost();

    void setFirewall(VFirewall vFirewall);

    void setPort(int port);

    void setHost(String host);
}
