package com.solvd.market.domain.orders;

import com.solvd.market.domain.payments.Payment;
import com.solvd.market.domain.shipments.Shipment;

public class Order {

    private Long id;
    private Payment payment;
    private Shipment shipment;
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
