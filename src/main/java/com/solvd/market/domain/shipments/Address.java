package com.solvd.market.domain.shipments;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlElement(name = "id")
    private Long id;
    @XmlElement(name = "street")
    private String street;
    @XmlElement(name = "homeNr")
    private Integer homeNr;
    @XmlElement(name = "flatNr")
    private Integer flatNr;
    @XmlElement(name = "city")
    private String city;
    @XmlElement(name = "postCode")
    private String postCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHomeNr() {
        return homeNr;
    }

    public void setHomeNr(Integer homeNr) {
        this.homeNr = homeNr;
    }

    public Integer getFlatNr() {
        return flatNr;
    }

    public void setFlatNr(Integer flatNr) {
        this.flatNr = flatNr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

}