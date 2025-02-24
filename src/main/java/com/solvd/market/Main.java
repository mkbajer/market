package com.solvd.market;

import com.solvd.market.domain.MarketPlace;
import com.solvd.market.domain.orders.Cart;
import com.solvd.market.domain.orders.Order;
import com.solvd.market.domain.payments.Payment;
import com.solvd.market.domain.products.Category;
import com.solvd.market.domain.products.Discount;
import com.solvd.market.domain.products.Product;
import com.solvd.market.domain.shipments.Address;
import com.solvd.market.domain.shipments.Shipment;
import com.solvd.market.domain.users.User;
import com.solvd.market.service.*;
import com.solvd.market.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        MarketPlaceService marketPlaceService = new MarketPlaceServiceImpl();
        UserService userService = new UserServiceImpl();
        CartService cartService = new CartServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        ProductService productService = new ProductServiceImpl();
        PaymentService paymentService = new PaymentServiceImpl();
        ShipmentService shipmentService = new ShipmentServiceImpl();

        MarketPlace marketPlace = new MarketPlace();
        marketPlace.setName("SuperMarket " + UUID.randomUUID().toString().substring(0, 2));
        marketPlace = marketPlaceService.create(marketPlace);

        User user = new User();
        user.setName("Jan");
        user.setSurname("Kowalski");
        user.setEmail("jan.kowalski@example.com");
        user.setPassword("password123");
        user.setPhone("123456789");
        user.setType(true);
        user.setActive(true);
        user = userService.create(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cart = cartService.create(cart);

        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(3500.00);
        product = productService.create(product, 1L);

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        cart.setProducts(productList);
        cartService.update(cart);

        Order order = new Order();
        order.setCart(cart);
        order = orderService.create(order);

        // Tworzenie płatności i przesyłki po utworzeniu zamówienia
        Payment payment = paymentService.retrieveById(1L);
        Shipment shipment = shipmentService.retrieveById(1L);

        // Aktualizacja zamówienia
        order.setPayment(payment);
        order.setShipment(shipment);

        log.info("Stworzono MarketPlace: {}", marketPlace.getName());
        log.info("Stworzono Użytkownika: {} {}", user.getName(), user.getSurname());
        log.info("Stworzono Koszyk ID: {}", cart.getId());
        log.info("Dodano Produkt: {} - {} PLN", product.getName(), product.getPrice());
        log.info("Stworzono Zamówienie ID: {}", order.getId());

        // Inicialize
        AddressService addressService = new AddressServiceImpl();
        ShipmentService shipmentService1 = new ShipmentServiceImpl();
        CategoryService categoryService = new CategoryServiceImpl();
        DiscountService discountService = new DiscountServiceImpl();
        ProductService productService1 = new ProductServiceImpl();

        Shipment shipment1 = new Shipment();
        shipment1.setCourier("DHL");
        shipment1 = shipmentService.create(shipment1, 4L);

        Address address = new Address();
        address.setStreet("Main Street");
        address.setHomeNr(10);
        address.setFlatNr(5);
        address.setCity("New York");
        address.setPostCode("10001");
        address = addressService.create(address, 3L);

        // Update shipment with address
        shipment1.setAddress(address);
        shipmentService.update(shipment1);

        Category category = new Category();
        category.setName("Electronics");
        category = categoryService.create(category);

        Discount discount = new Discount();
        discount.setName("Holiday Sale");
        discount.setAmount(10.0);
        discount = discountService.create(discount, 3L);
        discountService.addDiscountToCategory(discount.getId(), category.getId()); // Add discount to category

        Product product1 = new Product();
        product1.setName("Smartphone");
        product1.setPrice(799.99);
        product1.setCategory(category);
        product1 = productService.create(product1, category.getId());

        Product retrievedProduct = productService1.retrieveById(product.getId());
        log.info("Retrieved Product: {}", retrievedProduct.getName());

        Category retrievedCategory = categoryService.retrieveById(category.getId());
        log.info("Retrieved Category: {}", retrievedCategory.getName());

        Address retrievedAddress = addressService.retrieveById(address.getId());
        log.info("Retrieved Address: {}", retrievedAddress.getCity());

        Shipment retrievedShipment = shipmentService1.retrieveById(shipment.getId());
        log.info("Retrieved Shipment: {}", retrievedShipment.getAddress());

        Discount retrievedDiscount = discountService.retrieveById(discount.getId());
        log.info("Retrieved Discount: {}", retrievedDiscount.getName());

        product1.setPrice(749.99);
        productService.update(product1);

        address.setStreet("Broadway");
        addressService.update(address);

        discountService.delete(discount.getId());

        shipmentService1.delete(shipment1.getId());

        List<Product> productsWithCategories = productService1.retrieveAll();
        log.info("Products with categories:");
        productsWithCategories.forEach(p -> log.info("Product: {}, Category: {}", p.getName(), p.getCategory().getName()));
    }
}