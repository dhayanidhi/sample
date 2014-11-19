package com.training.dao;

import com.training.model.api.VNetwork;
import com.training.model.entity.PGroupPE;
import com.training.model.entity.VLocationPE;
import com.training.model.entity.VNetworkPE;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

/**
 * Created by pragati on 15.11.14.
 */
public class PbTrainingDAO extends AbstractDAO {

    private PbTrainingDAO(EntityManager entityManager){
        super(entityManager);
    }

    public static PbTrainingDAO getInstance(EntityManager entityManager){return new PbTrainingDAO(entityManager);}

    public PGroupPE getPGroupPE(UUID clusterId){
        return findByPrimaryKey(PGroupPE.class, clusterId);
    }

    public VLocationPE getVLocationPE(int locationId){
        return findByPrimaryKey(VLocationPE.class, locationId);
    }

    public VNetworkPE getVNetworkPE(UUID networkId){
        return findByPrimaryKey(VNetworkPE.class, networkId);
    }

    public void insertCluster(PGroupPE pGroupPE){
        insert(pGroupPE);
    }

    public void insertNetwork(VNetworkPE vNetworkPE){
        insert(vNetworkPE);
    }

    public VNetworkPE getVNetworkMeta(UUID networkId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VNetworkPE> criteriaQuery = criteriaBuilder.createQuery(VNetworkPE.class);
        Root network = criteriaQuery.from(VNetworkPE.class);
        network.join("location");
        criteriaQuery.select(criteriaBuilder.construct(VNetworkPE.class, network.get("uuid"),
                network.get("location"), network.get("pgroupId")));
        criteriaQuery.where(criteriaBuilder.equal(network.get("uuid"), networkId));

        TypedQuery<VNetworkPE> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList().get(0);
    }

    public void lockNetwork(String networkUuid){
        TypedQuery<VNetworkPE> query = entityManager.createNamedQuery(VNetworkPE.NetworkLock, VNetworkPE.class);
        query.setParameter(1, UUID.fromString(networkUuid));
        query.getResultList();
    }

    public void lockNamedNetwork(String networkUuid){
        TypedQuery<VNetworkPE> query = entityManager.createNamedQuery(VNetworkPE.NetworkNamedLock, VNetworkPE.class);
        query.setParameter("UUID", UUID.fromString(networkUuid));
        query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
        query.getResultList();
    }
}
