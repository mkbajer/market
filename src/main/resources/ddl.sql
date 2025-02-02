create database if not exists mydb;
use mydb;

create table if not exists market_places (
    id serial, # alias for BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

create table if not exists user (
    id serial,
    market_places_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(45) NOT NULL,
    surname VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    password VARCHAR(15) NOT NULL,
    phone INTEGER,
    type tinyint(0) NOT NULL DEFAULT 0,
    active tinyint(0) NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_market_places_id FOREIGN KEY(market_places_id) REFERENCES market_places(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create table if not exists cart (
    id serial,
    user_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_cart_user_id FOREIGN KEY(user_id) REFERENCES user(id)
);

create table if not exists orders (
    id serial,
    cart_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT orders_cart_id FOREIGN KEY (cart_id) REFERENCES cart(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create table if not exists shipment (
    id serial,
    orders_id BIGINT UNSIGNED NOT NULL,
    courier VARCHAR(45),
    PRIMARY KEY (id),
    CONSTRAINT shipment_orders_id FOREIGN KEY (orders_id) REFERENCES orders(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create table if not exists address (
    id serial,
    shipment_id BIGINT UNSIGNED NOT NULL,
    street VARCHAR(45),
    home_nr INTEGER,
    flat_nr INTEGER,
    city VARCHAR(45),
    post_code VARCHAR(45),
    PRIMARY KEY (id),
    CONSTRAINT fk_address_shipment_id FOREIGN KEY (shipment_id) REFERENCES shipment(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create table if not exists payment (
    id serial,
    orders_id BIGINT UNSIGNED NOT NULL,
    type VARCHAR(45),
    PRIMARY KEY (id),
    CONSTRAINT payment_orders_id FOREIGN KEY (orders_id) REFERENCES orders(id)
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
    category_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(45),
    price DOUBLE,
    PRIMARY KEY (id),
    CONSTRAINT product_category_id FOREIGN KEY (category_id) REFERENCES category(id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);