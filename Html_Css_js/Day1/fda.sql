Create database FDA;

use FDA;  -- Food delivery apk-

create table customers(customer_id int, name varchar(30), email varchar(30), city varchar(20));

insert into customers values (234, "Ankush", "ankush@gmail.com", "Varanasi");

insert into customers values (134, "Akash", "akashgmail.com", "Delhi"),
                              (264, "Anshu", "anshu@gmail.com", "Mumbai"),
                              (594, "Goutam", "goutam@gmail.com", "Noida");
                              
select*from customers;

-- Created customer table




create table restaurants(restaurant_id int, name varchar(20),cuisine_type varchar(30), city varchar(20));

insert into restaurants values(22232, "Starbucks", "coffee", "delhi"),
                               (22233, "Namastecafe","dosa", "Bengaluru"),
                               (22234, "Cafe inn", "Pizza", "delhi");

select*from restaurants;


-- Created restaurants table


create table orders( order_id int, customer_id int, restaurant_id int, order_amount int, order_date date);


insert into orders values (1, 234, 22232, 250, '2026-04-20'),
 (2, 134, 22233, 150, '2026-04-21'),
  (3, 264, 22234, 300, '2026-04-22'),
   (4, 594, 22232, 200, '2026-04-23');

select*from orders;

-- ============================================================================
