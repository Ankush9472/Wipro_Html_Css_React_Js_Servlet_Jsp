create database emp;

use emp;

create Table emp.employee (emp_id int primary key,
emp_name varchar(50),
password varchar(50),
emp_dept varchar(30),
salary int
);


insert into employee values(101, 'Ankush', 'ank123', 'IT', 55000),
(102, 'Akash', 'aka456', 'HR', 45000),
 (103, 'Neha', 'neh789', 'Finance', 60000),
(104, 'Ravi', 'rav321', 'IT', 52000),
   (105, 'Priya', 'pri654', 'Marketing', 48000),
(106, 'Amit', 'ami987', 'Sales', 47000),
(107, 'Sneha', 'sne111', 'HR', 46000);

-- Displaying all emp data

select*from employee;

-- Displaying who works in finance dept

select*from employee where emp_dept="finance";

-- Displaying 2 hign salries

select*from employee order by salary desc limit 2;


-- Dsiplaying salary as incremented by 2000

select salary+2000 as increment from employee;


-- ================DML commands==================


-- Updating data from employee 107 from HR to Manager

update employee set emp_dept="Manager" where emp_id=107;



-- Deleting data from employee table id=104

delete from employee where emp_id=104;


insert into employee values(101, 'Ankush', 'ank123', 'IT', 55000);



-- sorting table based on high salary

select*from employee order by salary desc;

-- Displaying 3 lowest salary


-- ============DDL Command=============

-- =============Alter command====================

-- Adding joining date cloumn 

alter table employee add joining_date date;




-- Adding joining date data  

update employee set joining_date = '2012-01-10' where emp_id = 101;
  update employee set joining_date = '1900-03-15' where emp_id = 103;
   update employee set joining_date = '2023-06-01' where emp_id = 106;
update employee set joining_date = '2001-11-25' where emp_id = 107;

-- Displaying data
select*from employee;


-- Renaming column

alter table employee rename column joining_date to joining;

select*from employee;


-- Dropping column using drop command

alter table employee drop column joining;

select*from employee;
