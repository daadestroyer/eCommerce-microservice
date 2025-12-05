INSERT INTO orders (order_status, total_price) VALUES ('PENDING', 1299.00);
INSERT INTO orders (order_status, total_price) VALUES ('CONFIRMED', 2499.50);
INSERT INTO orders (order_status, total_price) VALUES ('SHIPPED', 499.00);
INSERT INTO orders (order_status, total_price) VALUES ('DELIVERED', 3499.00);
INSERT INTO orders (order_status, total_price) VALUES ('CANCELLED', 899.00);

-- ORDER_ITEMS (each references order_id)
-- Note: use the same generated order ids (1..5) that Hibernate will create on empty DB
INSERT INTO order_item (product_id, quantity, order_id) VALUES (101, 1, 1);
INSERT INTO order_item (product_id, quantity, order_id) VALUES (102, 1, 2);
INSERT INTO order_item (product_id, quantity, order_id) VALUES (103, 2, 2);
INSERT INTO order_item (product_id, quantity, order_id) VALUES (104, 1, 3);
INSERT INTO order_item (product_id, quantity, order_id) VALUES (105, 1, 4);
INSERT INTO order_item (product_id, quantity, order_id) VALUES (106, 1, 4);
INSERT INTO order_item (product_id, quantity, order_id) VALUES (107, 1, 5);