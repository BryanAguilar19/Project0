---------- dropping tables if they exist --------
drop table Cars if exists;
drop table Company if exists;

---------- Creating tables to insert in format ----------
create table Cars (car_id int primary key, car_name varchar(50), year_made int, price float,
    mpg float ,company_id int, foreign key (company_id) references Company(company_id));

create table Company (company_id primary, company_name varchar(50), country_name varchar(50));

---------- Data Points -- Company ----------
insert into Company (company_id, company_name, country_name) values (1, 'Ford', 'USA' );

---------- Data Points -- Cars ----------
insert into Cars (car_id, car_name, year_made, mpg, company_id, company_id) values (1, Ford Bronco Sport, 37000, 25, 1);



