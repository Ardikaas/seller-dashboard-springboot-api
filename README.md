# E-Commerce Seller Dashboard API (Springboot)

Welcome to the E-Commerce Seller Dashboard API repository! This project provides a RESTful API for managing an e-commerce seller dashboard, built with Spring Boot. The API allows sellers to manage their products, orders, and inventory efficiently.

## Features

- Product Management: Add, update, delete, and view products.
- Order Management: Manage customer orders and track order status.
- Spring Boot: Robust and scalable application framework.
- MongoDB: NoSQL database for flexible data storage.

## Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- [Java 17+](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3.9+](https://maven.apache.org/download.cgi)
- [MongoDB](https://www.mongodb.com/)

### Installation

#### 1. Clone the repository:

```bash
git clone https://github.com/your-username/ecommerce-seller-dashboard-api.git
cd ecommerce-seller-dashboard-api
```

#### 2. Configure MongoDB:

Ensure MongoDB is running locally or update the application.properties file with your MongoDB connection details:

```bash
spring.data.mongodb.uri=mongodb://localhost:27017/your-database-name
```

#### 3. Build and run the application:

```bash
mvn clean install
mvn spring-boot:run
```

### Usage

Once the application is running, you can access the API at http://localhost:8080. Use tools like [Postman](https://www.postman.com/) to test the API endpoints.

### API Endpoints

#### User

- GET /user/: Retrieve all users
- GET /user/{id}: Retrieve user by ID
- POST /user/: Create a new user
- PUT /user/{id}: Update a user
- DELETE /user/{id}: Delete a user

#### Item

- GET /item/: Retrieve all items
- GET /item/{id}: Retrieve item by ID
- POST /item/: Create a new item
- PUT /item/{id}/add-variant: Add a variant to an item
- PUT /item/{id}: Update an item
- DELETE /item/{id}: Delete an item

#### Review

- GET /review/: Retrieve all reviews
- POST /review/: Create a review
- DELETE /review/{id}: Delete a review

#### Chart

- GET /chart/orders: Retrieve data for order chart
- GET /chart/sales: Retrieve data for sales chart

#### Order

- GET /order/: Retrieve all orders
- POST /order/: Create a new order
- PUT /order/{id}/status-inprocess: Update order status to "in process"
- PUT /order/{id}/status-readytoship: Update order status to "ready to ship"
- PUT /order/{id}/status-onshipping: Update order status to "on shipping"
- PUT /order/{id}/status-completed: Update order status to "completed"
- DELETE /order/{id}: Delete an order

### Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure your code follows the project's coding standards and includes appropriate tests.

- Fork the repository
- Create a new branch (`git checkout -b feature/your-feature-name`)
- Commit your changes (`git commit -m 'Add some feature'`)
- Push to the branch (`git push origin feature/your-feature-name`)
- Create a pull request

### Contact

If you have any questions or suggestions, feel free to contact us:

- Ardikaas: ardikaasbusiness@gmail.com
- GitHub Issues: [https://github.com/your-username/ecommerce-seller-dashboard-api/issues](https://github.com/your-username/ecommerce-seller-dashboard-api/issues)
