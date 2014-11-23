package com.training.model.entity;

import com.training.model.api.Location;
import com.training.model.api.VNetwork;
import com.training.model.api.VStorage;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.TypeConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pragati on 15.11.14.
 */
@Entity
@Table(name = "test_network")
@NamedQueries({
        @NamedQuery(name = VNetworkPE.NetworkNamedLock, query = "select nw from VNetworkPE nw where nw.uuid = :UUID")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = VNetworkPE.NetworkLock, query = "select * from test_network where c_uuid = ?1 for update")
})
public class VNetworkPE extends AbstractIdentity implements VNetwork<VMetadataImpl>{

    public static final String NetworkLock = "NetworkLock";
    public static final String NetworkNamedLock = "NetworkNamedLock";


    @ManyToOne
    @JoinColumn(name = "fk_location")
    private VLocationPE location;

    @TypeConverter(name = "uuid", dataType = Object.class, objectType = UUID.class)
    @Convert("uuid")
    @Column(name = "fk_pgroupId")
    private UUID pgroupId;

    @Embedded
    private VMetadataImpl vMetadata;

    @Transient
   // @OneToMany(targetEntity = VStoragePE.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch
   //         = FetchType.EAGER)
   // @JoinColumn(name = "fk_network_id", referencedColumnName = "c_uuid")
    private List<VStorage> vStorages = new ArrayList<VStorage>();

    @Override
    public List<VStorage> getVStorage() {
        return vStorages;
    }

    @Override
    public UUID getPGroupId() {
        return pgroupId;
    }

    @Override
    public void setPGroupId(UUID pGroupId) {
        this.pgroupId = pGroupId;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = (VLocationPE)location;
    }

    @Override
    public VMetadataImpl getvMetadata() {
        return vMetadata;
    }

    @Override
    public void setvMetadata(VMetadataImpl vMetadata) {
        this.vMetadata = vMetadata;
    }


}
