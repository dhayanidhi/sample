package com.training.access;

import com.training.api.TrainingAccess;
import com.training.dao.PbTrainingDAO;
import com.training.model.api.VNetwork;
import com.training.model.api.VirtualState;
import com.training.model.entity.PGroupPE;
import com.training.model.entity.VLocationPE;
import com.training.model.entity.VNetworkPE;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by pragati on 15.11.14.
 */
@Stateless(name = "TrainingAccess")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Remote(TrainingAccess.class)
public class TrainingAccessImpl implements TrainingAccess {

    @PersistenceContext(unitName = "training")
    EntityManager entityManager;

    public String insertNetwork(String cluster){
        UUID networkId = UUID.randomUUID();
        PbTrainingDAO dao = PbTrainingDAO.getInstance(entityManager);
        PGroupPE pGroupPE = dao.getPGroupPE(UUID.fromString(cluster));

        VNetworkPE vNetworkPE = new VNetworkPE();
        vNetworkPE.setLocation(pGroupPE.getLocation());
        vNetworkPE.setPGroupId(pGroupPE.getUuid());
        vNetworkPE.setUuid(networkId);
        vNetworkPE.setVirtualState(VirtualState.AVAILABLE);
        vNetworkPE.setName("unknown net name");
        dao.insertNetwork(vNetworkPE);

        return networkId.toString();
    }

    public String insertCluster(String location){
        UUID clusterId = UUID.randomUUID();
        PbTrainingDAO dao = PbTrainingDAO.getInstance(entityManager);
        VLocationPE vLocationPE = dao.getVLocationPE(Integer.valueOf(location));

        PGroupPE pGroupPE = new PGroupPE();
        pGroupPE.setLocation(vLocationPE);
        pGroupPE.setName("unknown grp name");
        pGroupPE.setUuid(clusterId);
        pGroupPE.setVirtualState(VirtualState.AVAILABLE);
        dao.insertCluster(pGroupPE);

        return clusterId.toString();
    }

    public String getValue(String val){
        return val;
    }

    public VNetwork getMetaInfo(String networkUuid){
        return PbTrainingDAO.getInstance(entityManager).getVNetworkMeta(UUID.fromString(networkUuid));
    }
}
