package com.training.access;

import com.training.api.LockTraining;
import com.training.api.TrainingAccess;
import com.training.dao.PbTrainingDAO;
import com.training.model.api.VNetwork;
import com.training.model.entity.VNetworkPE;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

/**
 * Created by pragati on 19.11.14.
 */
@Stateless(name = "LockTraining")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Remote(LockTraining.class)
public class LockTrainingImpl implements LockTraining {

    @PersistenceContext(unitName = "training")
    EntityManager entityManager;

    @Override
    public void lockNetwork(String networkUuid) throws Throwable{
        PbTrainingDAO dao = PbTrainingDAO.getInstance(entityManager);
        dao.lockNamedNetwork(networkUuid);
        Thread.sleep(30000);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public VNetworkPE getNetwork(String networkUuid) {
        PbTrainingDAO dao = PbTrainingDAO.getInstance(entityManager);
        return dao.getVNetworkPE(UUID.fromString(networkUuid));
    }
}
