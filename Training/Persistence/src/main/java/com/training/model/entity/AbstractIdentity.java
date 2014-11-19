package com.training.model.entity;

import com.training.model.api.Identity;
import com.training.model.api.VirtualState;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.TypeConverter;
import org.postgresql.util.PGobject;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by pragati on 15.11.14.
 */
@MappedSuperclass
public class AbstractIdentity implements Identity{

    @TypeConverter(name = "uuid", dataType = Object.class, objectType = UUID.class)
    @Convert("uuid")
    @Id
    @Column(name = "c_uuid")
    private UUID uuid;

    @Column(name="c_virtual_state")
    @Enumerated(EnumType.STRING)
    private VirtualState virtualState;

    @Column(name="c_name")
    private String name;

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public VirtualState getVirtualState() {
        return virtualState;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void setVirtualState(VirtualState virtualState) {
        this.virtualState = virtualState;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractIdentity that = (AbstractIdentity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        if (virtualState != that.virtualState) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (virtualState != null ? virtualState.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
