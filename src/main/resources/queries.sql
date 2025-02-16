UPDATE address SET street = 'Andersa' WHERE id = 1; -- name change of street where id = 1
UPDATE address SET home_nr = 33 WHERE id = 1; -- same for home nr
UPDATE address SET flat_nr = 44 WHERE id = 1; -- same for flat nr
UPDATE address SET city = 'Minsk' WHERE id = 2; -- same for city where id = 2
UPDATE address SET post_code = '55-555' WHERE id = 2; -- same for post code
UPDATE category SET name = 'eBooks' WHERE id = 1; -- changing name of category id 1
UPDATE discount SET amount = 22 WHERE id = 2; -- changing amount of categoryt id 2
UPDATE market_places SET name = 'BOOKs' WHERE id = 3; -- changing store with id 3 to BOOKs
UPDATE payment SET type = 'bank transfer' WHERE id = 1; -- changing type of payment on id 1
UPDATE product SET price = "123" WHERE id = 2; -- changing price on id 2 product

INSERT INTO user(name,surname,email,password,type,active) VALUES ('Jan','Testowy4','ee@wp.pl','123ewe321',1,1); -- adding user to table with null value for market_places
INSERT INTO market_places(name) VALUES('Testowy');

DELETE FROM payment WHERE id = 1 AND orders_id >= 10; -- deleting payment with id = 1 and from orders id greather or even to 10
DELETE FROM cart; -- deleting the whole carts values in cart ( all lines in table ) 
DELETE FROM category WHERE id = 5; -- deleting category with id equals to 5
DELETE FROM orders WHERE id > 10 OR  cart_id = 3; -- deleting order with id greather than 10 or where cart id = 3
DELETE FROM disocunt WHERE amount < 1; -- deleting all discount with amount lesser then 1
DELETE FROM discount WHERE name = 'Winter Sale'; -- deleting all discount named Winter Sale
DELETE FROM payment WHERE id = 1 AND orders_id >= 10; -- deleting all payment with id = 1 and where id of orders is even to or greater than 10
DELETE FROM payment WHERE type = 'Debit Card';  -- deleting all payment where type is equal to Debit Card
DELETE FROM payment WHERE id = 3; -- deleting payment where id is greater than 3
DELETE FROM user WHERE id > 4; -- deleting user where id is gretear than 4

ALTER TABLE category ADD valid VARCHAR(15); -- adding a row to the table
ALTER TABLE category DROP COLUMN valid; -- deleting the row from the table
ALTER TABLE category RENAME TO basket; -- renaming table name to basket
ALTER TABLE market_places CHANGE COLUMN name store VARCHAR(45); -- renaming coulumn name 'name' to 'store'
ALTER TABLE user MODIFY email VARCHAR(100) NOT NULL; -- changing size of the email field
ALTER TABLE address MODIFY post_code INT; -- modyfinig type of data on post_code to INT

SELECT name, COUNT(*) AS store_users FROM market_places GROUP BY name; -- counitng how many stores of a kind appears in market_places
SELECT MAX(amount)AS max_disc,name FROM discount GROUP BY name; -- finding the max amount of discount by the discount name
SELECT name, COUNT(*) AS store_users FROM market_places GROUP BY name HAVING COUNT(*) > 2; -- showing all market places where there is more than 2 users
SELECT price,name FROM product HAVING price > 100 ORDER BY price DESC; -- showing all product with value over 100 descending order
SELECT cart_id, COUNT(*) AS total_orders FROM orders GROUP BY cart_id HAVING COUNT(*) > 5; -- total number of orders per cart where the number of orders is greater than 5

SELECT a.street, a.city, s.courier FROM address a JOIN shipment s ON a.shipment_id = s.id; -- joining shipment courier name with street and city where the package will go, using inner join to catch only rows with matching data in both tables
SELECT p.name, c.name AS category_name FROM product p RIGHT JOIN category c ON p.category_id =c.id; -- joining product and its category name , showing all rows from the right (category) table with matched rows from the right table 
SELECT m.name AS store, u.name, u.surname FROM user u LEFT JOIN market_places m ON u.market_places_id = m.id; -- joining user and user name surname with name of store, all rows from left (user) table with matched rows
-- MySQL does not support OUTHER JOIN
SELECT m.name AS store, u.name, u.surname FROM user u LEFT JOIN market_places m ON u.market_places_id = m.id 
UNION 
SELECT m.name AS store, u.name, u.surname FROM user u RIGHT JOIN market_places m ON u.market_places_id = m.id; -- using union for outher join, seleciting all users and stores including unmatched records

SELECT u.name AS user_name, u.surname AS user_surname, c.id AS cart_id, o.id AS order_id, 
       s.courier AS shipment_courier, a.city AS delivery_city, a.street AS delivery_street, a.post_code AS delivery_post_code
FROM user u
JOIN cart c ON u.id = c.user_id
JOIN orders o ON c.id = o.cart_id
JOIN shipment s ON o.id = s.orders_id
JOIN address a ON s.id = a.shipment_id
WHERE u.active = 1;