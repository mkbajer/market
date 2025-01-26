INSERT INTO market_places(name) VALUES ('Amazon');
INSERT INTO market_places(name) VALUES
	('STOKROTKA'),
    ('ALLEGRO');

INSERT INTO user(name,surname,email,password,phone,type,active,market_places_id) VALUES 
	('Jan','Testowy','jan.testowy@solvd.com','orangutan1',null,0,1,1),
    ('Beata','Nowak','b.nowak@wp.pl','apple123',785444333,0,0,2);

INSERT INTO cart(user_id) VALUES (1);
INSERT INTO cart(user_id) VALUES (2);

INSERT INTO orders(id,cart_id) VALUES (1,1);
INSERT INTO orders(id,cart_id) VALUES (2,2);

INSERT INTO shipment(courier,orders_id) VALUES ('DHL',1),('DPD',2);

INSERT INTO address(street,home_nr,flat_nr,city,post_code,shipment_id) VALUES
	('Sportowa',1,6,'Bydgoszcz','85-550',1),
    ('Marszalkowska',110,22,'Warszawa','00-340',2);
    
INSERT INTO category(name) VALUES
	('Books'),
    ('Hats');
    
INSERT INTO discount(name,amount) VALUES
	('FLASH SALE 22.23', 25.5),
    ('END OF SERIES 24', 50);
    
INSERT INTO category_discounts(category_id,discount_id) VALUES
	(1,2),
    (2,1);
    
INSERT INTO payment(type,orders_id) VALUES
	('CREDIT CARD',1),
    ('BLIK',2);
    
INSERT INTO product(name, price,category_id) VALUES
	('LOTR pt.1',100,1),
    ('SHINING',140,1),
    ('FULLCAP',2200,2);
    
INSERT INTO cart_products(cart_id,product_id) VALUES
	(1,1),
    (1,2),
    (2,3);