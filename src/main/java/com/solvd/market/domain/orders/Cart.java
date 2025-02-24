package com.solvd.market.domain.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.solvd.market.domain.products.Product;
import com.solvd.market.domain.users.User;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

import java.util.List;

@JsonPropertyOrder({"id", "user", "products"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Cart {

    @JsonProperty("id")
    @XmlElement(name = "id")
    private Long id;

    @JsonProperty("user")
    @XmlElement(name = "user")
    private User user;

    @JsonProperty("products")
    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
