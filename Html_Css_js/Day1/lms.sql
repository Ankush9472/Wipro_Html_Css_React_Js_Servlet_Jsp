create database LMS;
-- to get inside the database or change the database 
use lms;


create database EMS;
use ems;

-- create a table
create table employees(emp_id int , emp_name varchar(30) ,password varchar(40) not null , emp_dept varchar(40) not null  ,salary int);

-- To see table structure
describe employees;

-- to retrieve/ view / see  the records or data or rows of a table 
select * from employees;

-- insert the values in a table 
insert into empl(oyees values(101, "Niti Dwivedi" , "pass@123" , "Training" , 50000),
							(102, "Nitin Mehta" , "password@123" , "Admin" , 60000),
                            (103, "Jaya Kishori" , "jaya@123" , "Finance" , 80000);
                            
select * from employees;

-- ====================================================================