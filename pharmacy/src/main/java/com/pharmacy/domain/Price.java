package com.pharmacy.domain;

import com.pharmacy.domain.Pharmacy;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Alexander on 02.01.2016.
 */
@Entity
@Table(name = "price")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Price {

    private float suggestedRetailPrice;
    private String extraShippingSuffix;
    private int discount;
    private float price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    /**
     * @return the suggestedRetailPrice
     */
    public float getSuggestedRetailPrice() {
        return suggestedRetailPrice;
    }

    /**
     * @param suggestedRetailPrice the suggestedRetailPrice to set
     */
    public void setSuggestedRetailPrice(float suggestedRetailPrice) {
        this.suggestedRetailPrice = suggestedRetailPrice;
    }

    /**
     * @return the extraShippingSuffix
     */
    public String getExtraShippingSuffix() {
        return extraShippingSuffix;
    }

    /**
     * @param extraShippingSuffix the extraShippingSuffix to set
     */
    public void setExtraShippingSuffix(String extraShippingSuffix) {
        this.extraShippingSuffix = extraShippingSuffix;
    }

    /**
     * @return the discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the pharmacy
     */
    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    /**
     * @param pharmacy the pharmacy to set
     */
    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    @Override
    public String toString() {
        return "Price{" + "suggestedRetailPrice=" + suggestedRetailPrice + ", extraShippingSuffix=" + extraShippingSuffix + ", discount=" + discount + ", price=" + price + ", pharmacy=" + pharmacy + '}';
    }
}
