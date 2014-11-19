package com.training.model.entity;

import com.training.model.api.Location;
import com.training.model.api.LocationPricing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by pragati on 15.11.14.
 */
@Entity
@Table(name = "test_location_pricing")
public class VLocationPricingPE implements LocationPricing{

    @Id
    @Column(name = "c_id")
    private Integer id;

    @Column(name = "c_price")
    private String price;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    @Override
    public void setValue(String value){
        this.price = value;
    }

    @Override
    public String getValue() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VLocationPricingPE that = (VLocationPricingPE) o;

        if (id != that.id) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
