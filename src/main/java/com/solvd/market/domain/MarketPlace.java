package com.solvd.market.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.solvd.market.domain.users.User;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@JsonPropertyOrder({"id", "name", "users"})
@XmlRootElement(name = "marketplace")
@XmlAccessorType(XmlAccessType.FIELD)
public class MarketPlace {

    @JsonProperty("id")
    @XmlElement(name = "id")
    private Long id;

    @JsonProperty("name")
    @XmlElement(name = "name")
    private String name;

    @JsonProperty("users")
    @XmlElementWrapper(name = "users")
    @XmlElement(name = "user")
    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}