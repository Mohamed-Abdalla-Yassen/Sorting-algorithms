1. How to Run the Application
This project is built using a decoupled architecture with a Spring Boot backend (handling the sorting logic, comparisons, and file generation) and an Angular frontend (handling the graphical user interface and sorting visualization).

To run this application locally, you will need to start both the backend server and the frontend development server.

Prerequisites
Before running the application, ensure you have the following installed on your machine:

Java Development Kit (JDK): Version 17 or higher (for Spring Boot).

Node.js and npm: Required for running the Angular frontend.

Git: To clone the repository.

Step 1: Start the Spring Boot Backend
The backend serves as the API for sorting algorithms and data generation.

Open your terminal and navigate to the backend directory of the project:

Bash
cd path/to/your/backend-folder
Run the Spring Boot application using the Maven wrapper (no local Maven installation required):

On Windows:

DOS
mvnw.cmd spring-boot:run
On Mac/Linux:

Bash
./mvnw spring-boot:run
The backend server will start, typically running on http://localhost:8080. Leave this terminal window open.

Step 2: Start the Angular Frontend
The frontend contains the GUI for the Sorting Comparison and Visualization modes. Since the node_modules folder is excluded from version control, you must install the project dependencies first.

Open a new terminal window and navigate to the frontend directory:

Bash
cd path/to/your/frontend-folder
Install the dependencies: This reads the package.json file and downloads all required libraries to a local node_modules folder.

Bash
npm install
Once the installation is complete, start the Angular development server:

Bash
ng serve
(Alternatively, if the Angular CLI is not installed globally, you can run npm start)

Open your web browser and navigate to http://localhost:4200/ to access the application's Graphical User Interface.
