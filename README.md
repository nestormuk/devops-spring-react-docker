
This project consists of a React frontend and a Spring Boot backend connected to a MariaDB database. Follow the steps below to set up and run both parts of the application.

Prerequisites

Make sure you have the following installed:
- Node.js (for React)
- Maven (for Spring Boot)
- MariaDB database

Frontend (React)
1. Navigate to the React project directory.
2. Install dependencies:
 
   npm install

3. Start the React development server:

   npm start

The app will run on `http://localhost:3000` by default.
Backend (Spring Boot)
1. Update the `application.properties` file to connect to MariaDB:

   spring.datasource.url=jdbc:mariadb://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update

2. Navigate to the Spring Boot project directory.
3. Clean install dependencies:
 
   nvm clean install

4. Run the Spring Boot application:

   mvn spring-boot:run

The backend will run on `http://localhost:8080` by default.
Running Both Frontend and Backend
Make sure both React and Spring Boot apps are running. React communicates with the backend API.

Troubleshooting
- Ensure MariaDB is running and accessible.
- Check the correct Node.js version is used for the React app.
- Look at console logs for error details.
