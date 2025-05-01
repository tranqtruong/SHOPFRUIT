# 🍊 SHOPFRUIT – Fruit E-commerce Website (Spring MVC)

A full-featured e-commerce web application for selling fruits online, developed using legacy Java technologies including Spring MVC (XML configuration), Hibernate ORM, JSP/JSTL, and SQL Server. The application supports user registration, product browsing, shopping cart management, order placement, email notifications, and administrative functionalities.

## 🧰 Tech Stack

- **Backend:**
  - Java 8
  - Spring MVC (XML-based configuration)
  - Hibernate ORM (manual session management)
  - JavaMail API for sending emails
  - Apache Commons FileUpload for handling file uploads

- **Frontend:**
  - JSP with JSTL
  - Bootstrap 4 for responsive design
  - Server-side rendering (no client-side JavaScript frameworks)

- **Database:**
  - SQL Server (with Hibernate mapping)

- **Build Tool:**
  - Maven

## 🚀 Features

### 👤 User Side

- User registration and login
- Password recovery via email
- Product browsing and search functionality
- Shopping cart: add, update, and remove items
- Order placement with email confirmation
- View order history and cancel pending orders
- Profile management

### 🛠️ Admin Side

- Admin authentication
- Product management: add, edit, delete, and toggle visibility
- User account management: activate, lock, and delete accounts
- Order management: confirm and cancel orders
- View detailed order information

## 📧 Email Notifications

- **Order Confirmation:** Sends an email to the user upon successful order placement.
- **Password Recovery:** Sends a password reset link to the user's registered email address.

## 📁 File Upload

- Users can upload profile pictures.
- Admins can upload product images.
- Uploaded files are stored on the server and linked to the respective user or product profiles.

## 🗃️ Database Design

- **Users:** Stores user information, roles (user/admin), and account status.
- **Products:** Contains product details, including name, description, price, and availability status.
- **Carts:** Temporary storage for users' selected products before order placement.
- **Orders:** Records finalized purchases with status tracking (e.g., pending, confirmed, canceled).

## 📦 How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/tranqtruong/SHOPFRUIT.git
   ```

2. **Import the project:**
   - Open your IDE (e.g., Eclipse or IntelliJ IDEA).
   - Import the project as a Maven project.

3. **Configure the database:**
   - Set up a SQL Server database.
   - Update the database connection details in `applicationContext.xml` or the relevant configuration files.

4. **Build and deploy:**
   - Use Maven to build the project.
   - Deploy the WAR file to a servlet container like Apache Tomcat.

5. **Access the application:**
   - Navigate to `http://localhost:8080/SHOPFRUIT/` in your web browser.

## 📚 Learning Objectives

- Understand the implementation of the MVC pattern using Spring MVC with XML configuration.
- Gain experience with Hibernate ORM for database interactions.
- Learn to handle file uploads and manage uploaded content.
- Implement email functionalities using JavaMail API.
- Develop a full-stack web application with server-side rendering.
