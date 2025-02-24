package com.solvd.market.domain.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.solvd.market.domain.payments.Payment;
import com.solvd.market.domain.shipments.Shipment;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@JsonPropertyOrder({"id", "payment", "shipment", "cart"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

    @JsonProperty("id")
    @XmlElement(name = "id")
    private Long id;
    @JsonProperty("payment")
    @XmlElement(name = "payment")
    private Payment payment;
    @JsonProperty("shipment")
    @XmlElement(name = "shipment")
    private Shipment shipment;
    @JsonProperty("cart")
    @XmlElement(name = "cart")
    private Cart cart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
