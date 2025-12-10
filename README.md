<div align="center">
<h1>Onez - E-Commerce Website</h1>
<img  width="350" height="350" alt="logo" src="https://github.com/user-attachments/assets/29f86d35-da34-4135-a7bc-58edb24880ad" />
</div>

<h2>Overview</h2>

Onez is a simple e-commerce web application developed using Java Enterprise Edition (JEE) technologies, Jakarta EE, and standard web technologies like HTML, CSS, and JavaScript. The application supports two user roles: Admin and Customer, providing role-based functionalities for managing and purchasing products.

<h2>Features</h2>
<h3>Admin</h3>

Add, update, and delete products.

View all customer orders.

Manage product categories.

Access admin dashboard with analytics.

<h3>Customer</h3>

Browse and search products.

Add products to cart.

Place orders and view order history.

Update personal profile information.

<h2>Technologies Used</h2>

Backend: Java, JEE, Jakarta EE, Servlets, JSP

Frontend: HTML, CSS, JavaScript

Database: MySQL (or any relational database supported with JDBC)

IDE: Eclipse IDE

Other: JSTL, JDBC for database connectivity

<h2>Project Structure</h2>

```bash
Onez/
│
├── src/                   # Java source files (Servlets, Models, Utils)
├── WebContent/            # JSP, HTML, CSS, JS files
├── WebContent/WEB-INF/    # web.xml, config files
├── lib/                   # External libraries (JAR files)
└── README.md              # Project documentation
```

<h2>Installation</h2>

Clone the repository

```bash
git clone <repository-url>
```

Setup database

```bash
Create a database named onez_db.
```

Import the provided SQL schema from database/onez_schema.sql.

<h3>Configure database connection</h3>

Update DBUtil.java (or your utility class) with your database credentials.

<h3>Import project in Eclipse</h3>

Open Eclipse IDE → File → Import → Existing Maven/Java EE Project.

<h3>Build the project.</h3>

Run on server

Use Tomcat or any compatible Jakarta EE server.

Access the website: http://localhost:8080/Onez

<h2>Usage</h2>

Admin Login: /admin/login.jsp

Customer Login/Register: /customer/login.jsp or /customer/register.jsp

Browse Products: /products.jsp

Place Orders: Add products to cart → Checkout

<h2>Contribution</h2>

Fork the repository.

Create a feature branch (git checkout -b feature-name).

Commit your changes (git commit -m "Add feature").

Push to the branch (git push origin feature-name).

Create a pull request.
