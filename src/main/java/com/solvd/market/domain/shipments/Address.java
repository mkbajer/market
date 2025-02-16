package com.solvd.market.domain.shipments;

public class Address {

    private Long id;
    private String street;
    private Integer homeNr;
    private Integer flatNr;
    private String city;
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