-- dropping tables if they exist
drop table Cars if exists;
drop table Company if exists;

--Creating tables to insert informat
create table Cars (car_id int primary key, car_name varchar(50), price float,
    mpg float ,company_id int, foreign key (company_id) references Company(company_id));

create table Company (company_id primary, company_name varchar(50), country_name varchar(50));

