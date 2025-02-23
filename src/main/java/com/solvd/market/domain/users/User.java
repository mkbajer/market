package com.solvd.market.domain.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.solvd.market.domain.orders.Cart;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

import java.util.List;

@JsonPropertyOrder({"id", "name", "surname", "email", "phone", "type", "active", "carts"})
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @JsonProperty("id")
    @XmlElement(name = "id")
    private Long id;

    @JsonProperty("name")
    @XmlElement(name = "name")
    private String name;

    @JsonProperty("surname")
    @XmlElement(name = "surname")
    private String surname;

    @JsonProperty("email")
    @XmlElement(name = "email")
    private String email;

    @JsonProperty("password")
    @XmlElement(name = "password")
    private String password;

    @JsonProperty("phone")
    @XmlElement(name = "phone")
    private String phone;

    @JsonProperty("type")
    @XmlElement(name = "type")
    private Boolean type;

    @JsonProperty("active")
    @XmlElement(name = "active")
    private Boolean active;

    @JsonProperty("carts")
    @XmlElementWrapper(name = "carts")
    @XmlElement(name = "carts")
    private List<Cart> carts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
