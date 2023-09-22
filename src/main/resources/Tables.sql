---------- dropping tables if they exist --------
drop table Cars if exists;
drop table Company if exists;

---------- Creating tables to insert in format ----------
create table Company (
    company_id int primary key,
    company_name varchar(50),
    country_name varchar(50)
);

create table Cars (
    car_id int primary key,
    car_name varchar(50),
    year_made int,
    price float,
    mpg float,
    company_id int,
    foreign key (company_id) references Company(company_id)
);

---------- Data Points -- Company ----------
insert into Company (company_id, company_name, country_name) values (1, 'Ford', 'USA');
insert into Company (company_id, company_name, country_name) values (2, 'Nissan', 'Japan');
insert into Company (company_id, company_name, country_name) values (3, 'Ferrari', 'Italy');
insert into Company (company_id, company_name, country_name) values (4, 'Toyota', 'Japan');
insert into Company (company_id, company_name, country_name) values (5, 'Mazda', 'Japan');
insert into Company (company_id, company_name, country_name) values (6, 'Kia', 'South Korea');
insert into Company (company_id, company_name, country_name) values (7, 'BMW', 'Germany');
insert into Company (company_id, company_name, country_name) values (8, 'Jeep', 'USA');

---------- Data Points -- Cars ----------
-- Ford Cars --
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (1, 'Ford Bronco Sport', 2021, 37000, 24, 1);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (2, 'Ford Mustang', 1965, 42000, 12, 1);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (3, 'Ford Fusion', 2019, 30000, 20, 1);

-- Nissan Cars --
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (4, 'Nissan Altima', 2005, 23000, 23, 2);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (5, 'Nissan Maxima', 2016, 20000, 25, 2);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (6, 'Nissan Rogue', 2022, 35000, 30, 2);

-- Ferrari Cars --
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (7, 'Ferrari 458 Italia', 2014, 233000, 14, 3);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (8, 'Ferrari Testarossa', 1984, 265000, 16, 3);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (9, 'Ferrari F8 Tributo', 2021, 275000, 18, 3);

-- Toyota Cars --
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (10, 'Toyota Camry', 2000, 12000, 25, 4);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (11, 'Toyota Prius', 2015, 22000, 50, 4);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (12, 'Toyota Corolla', 2022, 25000, 40, 4);

-- Mazda Cars --
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (13, 'Mazda MX-5 Miata', 1990, 15000, 28, 5);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (14, 'Mazda CX-5', 2018, 25000, 28, 5);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (15, 'Mazda3', 2023, 23000, 32, 5);

-- Kia Cars --
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (16, 'Kia Optima', 2010, 18000, 26, 6);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (17, 'Kia Sportage', 2019, 27000, 24, 6);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (18, 'Kia Seltos', 2021, 21000, 30, 6);

-- BMW Cars --
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (19, 'BMW 3 Series', 2005, 17000, 20, 7);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (20, 'BMW X5', 2017, 50000, 18, 7);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (21, 'BMW 2 Series', 2022, 44000, 33, 7);

-- Jeep Cars --
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (22, 'Jeep Wrangler', 1995, 12000, 17, 8);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (23, 'Jeep Grand Cherokee', 2012, 15000, 20, 8);
insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (24, 'Jeep Compass', 2020, 27000, 26, 8);
