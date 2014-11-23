package com.training.dao;

import com.training.model.api.Location;
import com.training.model.api.VMetadata;
import com.training.model.api.VNetwork;
import com.training.model.dto.VNetworkDTO;
import com.training.model.entity.PGroupPE;
import com.training.model.entity.VLocationPE;
import com.training.model.entity.VNetworkPE;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
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

    public VNetworkDTO getVNetworkMeta(UUID networkId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VNetworkDTO> criteriaQuery = criteriaBuilder.createQuery(VNetworkDTO.class);
        Metamodel metamodel = entityManager.getMetamodel();
        EntityType<VNetworkPE> entityType = metamodel.entity(VNetworkPE.class);
        Root<VNetworkPE> root = criteriaQuery.from(entityType);

        List<Selection<?>>selections = new ArrayList<Selection<?>>();
        selections.add(root.get("uuid"));
        selections.add(root.get("pgroupId"));
        selections.add(root.get("vMetadata"));
/*        Join<VNetworkPE, VMetadata> join1 = root.join("vMetadata");
        selections.add(join1.get("creationTime"));*/
        Join<VNetworkPE, Location> join2 = root.join("location");
        selections.add(join2.get("path"));

        criteriaQuery.select(criteriaBuilder.construct(VNetworkDTO.class, selections.toArray(new Selection[0])));
        criteriaQuery.where(criteriaBuilder.equal(root.get("uuid"), networkId));
        List<VNetworkDTO> networkDTOs = entityManager.createQuery(criteriaQuery).getResultList();
        return networkDTOs.get(0);
    }

    public List<VNetworkDTO> getVNetworkMeta(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VNetworkDTO> criteriaQuery = criteriaBuilder.createQuery(VNetworkDTO.class);
        Metamodel metamodel = entityManager.getMetamodel();
        EntityType<VNetworkPE> entityType = metamodel.entity(VNetworkPE.class);
        Root<VNetworkPE> root = criteriaQuery.from(entityType);

        List<Selection<?>>selections = new ArrayList<Selection<?>>();
        selections.add(root.get("uuid"));
        selections.add(root.get("pgroupId"));
        selections.add(root.get("vMetadata"));
        Join<VNetworkPE, Location> join2 = root.join("location");
        selections.add(join2.get("path"));

        criteriaQuery.select(criteriaBuilder.construct(VNetworkDTO.class, selections.toArray(new Selection[0])));
        List<VNetworkDTO> networkDTOs = entityManager.createQuery(criteriaQuery).getResultList();
        return networkDTOs;
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
