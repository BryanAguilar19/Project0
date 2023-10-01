# User Story
## Cars Project

### Stories:
- As a User, I should be able to view list of cars
- As a User, I should be able to filter and Sort list of cars based on company/year/model/MPG/Price/country
- As a User, I should be able to insert a car
- As a User, I should be able to view list of Companies
- As a User, I should be able to insert a Company

### Design Section:
 - Cars: int carID, string company, string model, int year, double price, double milesPerGallon
 - Company: int companyID, string name, string country

## Class Descriptions
### Controller: 
File used for handling user input processing it, and coordinating with other parts of the application, it manages web interaction and ensure proper data flow.

### DAO: 
Data Access Object (DAO) file typically contains classes and methods responsible for interacting with a database or data source
  - CarsDAO: Class used for handling encapsulation of all data access operations related to cars within the application.
    - Methods:
      - insertCars: inserts a new car record into the database.
      - filterCars: retrieving the list of cars from the Database based on the Query Parameters
  - CompanyDAO: Class used for handling encapsulation of all data access operations related to companies within the application.
    - Methods:
      - getCompanyList: inserts a new company record into the Database
      - insertCompany: retrieving the list of companies from the Database based on the Query Parameters 
### Model 
File used for encapsulation of properties and behaviors of data objects, easier to work with and manipulate data in the application
  - Cars: encapsulates various properties and attributes that describe a car
  - Company: encapsulates various properties and attributes that describe a company
### Service 
Contains classes that serve as an intermediary between the controller and the model layers of the application
  - CarsService: Performs the CRUD operations on Cars
    - Methods
      - filterCars: Filter cars based on various criteria.
      - insertCar: Create a new car and insert it into the database.
  - CompanyService: Performs the CRUD operations on Company
    - Methods
      - getCompany: Filter cars based on various criteria
      - addCompany: Creates a new company and inserts into the Database
### Util
File that contains utility classes and methods that offer common functionalities and help streamline various tasks within the application
  - Application: Class that runs the starting point (main method) of the application
  - ConnectionSingleton: Class will be utilized to create an active connection to the database.

# Application Programming Interface (Controller File)
Within the following section contains information of the following API established:

## Company API: Get All Companies

### Endpoint
`GET /api/v1/companies`

### Description
This endpoint retrieves a list of all companies or filters the results based on query parameters.

### Query Parameter
- `Company_Id`: Filter companies by Company ID.
- `Name`: Filter companies by name.
- `Country`: Filter companies by country.


## Company API: Add a Company

### Endpoint
`POST /api/v1/companies`

### Description
This endpoint will allow you to insert a company

### Request Body
Within the request body, provide a JSON object with the following fields
- `Company_Id`: The ID of the company.
- `Name`: Name of the company.
- `Country`: Location of the country.


## Cars API: Get all Cars

### Endpoint
`GET /api/v1/cars`

### Description
This endpoint retrieves a list of all cars in the database or filters the results based on query parameters.

### Query Parameter
- `minPrice`: Filters Minimum price.
- `maxPrice`: Filters Maximum price.
- `minMpg`: Filters Minimum MPG. 
- `maxMpg`: Filters Maximum MPG.
- `minYear`: Filters Minimum year.
- `maxYear`: Filters Maximum year.
- `sortMpgPriceRatio`: Sorting cars in descending MpgPrice Ratio.
- `companyID`: Filters Company ID.


## Cars API: Add a Car

### Endpoint
`POST /api/v1/cars`

### Description
This endpoint creates a new car

### Request Body
Within the request body, provide a JSON object with the following fields
- `car_id`: The ID of the car
- `car_name`: The name of the car
- `year_made`: The year the car was made
- `price`: The pricing of the car
- `mpg`: The Miles-Per-Gallon of the car
- `company_id`: The ID of the Company where car was made







