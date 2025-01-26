create database if not exists mydb;
use mydb;

create table if not exists market_places (
    id serial, # alias for BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

create table if not exists users (
    id serial,
    market_places_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(45) NOT NULL,
    surname VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    password VARCHAR(15) NOT NULL,
    phone VARCHAR(45),
    type tinyint(0) NOT NULL DEFAULT 0,
    active tinyint(0) NOT NULL DEFAULT 0,
    market_places_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_users_market_places_id FOREIGN KEY(market_places_id) REFERENCES market_places(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create table if not exists address (
    id serial,
    shipment_id BIGINT UNSIGNED NOT NULL,
    street VARCHAR(45),
    home INTEGER,
    flat INTEGER,
    city VARCHAR(45),
    postcode VARCHAR(45),
    PRIMARY KEY (id),
    CONSTRAINT fk_address_shipment_id FOREIGN KEY (shipment_id) REFERENCES shipment(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create table if not exists shipment (
    id serial,
    order_id BIGINT UNSIGNED NOT NULL,
    courier VARCHAR(45),
    PRIMARY KEY (id),
    CONSTRAINT shipment_order_id FOREIGN KEY (order_id) REFERENCES orders(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create table if not exists orders (
    id serial,
    cart_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT orders_cart_id FOREIGN KEY (cart_id) REFERENCES cart(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create table if not exists cart (
    id serial,
    PRIMARY KEY (id)
);

create table if not exists payment (
    id serial,
    order_id BIGINT UNSIGNED NOT NULL,
    type VARCHAR(45),
    PRIMARY KEY (id),
    CONSTRAINT payment_order_id FOREIGN KEY (order_id) REFERENCES orders(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create table if not exists category (
    id serial,
    name VARCHAR(45),
    PRIMARY KEY (id)
);

create table if not exists discount (
    id serial,
    category_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(45),
    amount DOUBLE,
    PRIMARY KEY (id),
    CONSTRAINT discount_category_id FOREIGN KEY (category_id) REFERENCES category(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create table if not exists product (
    id serial,
    category_id UNSIGNED NOT NULL,
    name VARCHAR(45),
    price DOUBLE,
    PRIMARY KEY (id),
    CONSTRAINT product_category_id FOREIGN KEY (category_id) REFERENCES category(id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);