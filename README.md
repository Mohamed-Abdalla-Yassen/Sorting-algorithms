# 📊 Sorting Algorithms Visualizer & Benchmarking Tool
[![Java Spring Boot](https://img.shields.io/badge/Backend-Spring%20Boot-brightgreen)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Frontend-Angular%20v20-red)](https://angular.io/)
## 📝 Description
An interactive, full-stack web app designed to bring sorting algorithms to life! It serves two main purposes: showing step-by-step animations of how arrays are sorted, and providing a benchmarking tool to compare the speed of different algorithms.

Built with a fast **Spring Boot** backend and a responsive **Angular** frontend, the app ensures smooth visual animations and highly accurate performance tracking.

## ✨ Features

### 🔍 1. Interactive Sorting Visualization
* **Step-by-Step Playback:** Navigate forward and backward through the exact frames of the sorting process.
* **Auto-Run Animation:** Watch the algorithms sort in real-time with an adjustable speed slider.
* **Live Statistics:** Tracks the exact number of array comparisons and interchanges (swaps) at every step.
* **Dynamic Highlighting:** Pointers visually highlight which specific elements are currently being compared or swapped.

### ⏱️ 2. High-Precision Benchmarking (Comparison Mode)
* **Multiple Runs & Aggregation:** Execute algorithms hundreds or thousands of times to calculate the Minimum, Maximum, and Average Execution Times (in nanoseconds).
* **Custom Data Generation:** Test algorithms against various data profiles, including Random, Fully Sorted, and Reverse-Sorted arrays.
* **File Upload Support:** Upload custom comma-separated `.txt` files to benchmark specific, real-world datasets.

### 🧠 3. Supported Algorithms
The application implements both visualization-tracked and purely optimized versions of six classic algorithms:
* Bubble Sort
* Selection Sort
* Insertion Sort
* Merge Sort
* Quick Sort
* Heap Sort

### 🚀 4. Advanced Architecture
* **Dual-Track Engine:** The backend separates visualization logic from benchmarking logic, guaranteeing that the massive overhead of tracking UI steps does not ruin the accuracy of the runtime metrics.
* **Angular Signals:** The frontend UI uses modern Angular Signals (`signal()`) for highly efficient, pinpoint DOM updates, preventing the browser from lagging during high-speed sorting animations of large arrays.

## 🛠️ Tech Stack
* **Frontend:** Angular, TypeScript, HTML/CSS
* **Backend:** Java, Spring Boot
* **Data Analysis:** Python, Google Colab (Pandas, Matplotlib)
https://colab.research.google.com/drive/1x8vFNpLd38E7T64pqw7jvX2r4qmbnYOG?usp=sharing

---

## 🏃 1. How to Run the Application
This project is built using a decoupled architecture with a Spring Boot backend (handling the sorting logic, comparisons, and file generation) and an Angular frontend (handling the graphical user interface and sorting visualization).

To run this application locally, you will need to start both the backend server and the frontend development server.

### Prerequisites
Before running the application, ensure you have the following installed on your machine:
* **Java Development Kit (JDK):** Version 21 or higher (for Spring Boot).
* **Node.js and npm:** Required for running the Angular frontend.

### Step 1: Start the Spring Boot Backend
The backend serves as the API for sorting algorithms and data generation.

1. Open your terminal and navigate to the backend directory of the project:
   ```bash
   cd path/to/your/backend-folder
2. Run the Spring Boot application using the Maven wrapper (no local Maven installation required):
### On Windows
    mvnw.cmd spring-boot:run
### On Mac/Linux:
    ./mvnw spring-boot:run
3. The backend server will start, typically running on http://localhost:8080. Leave this terminal window open.

### Step 2: Start the Angular Frontend
The frontend contains the GUI for the Sorting Comparison and Visualization modes. Since the node_modules folder is excluded from version control, you must install the project dependencies first.

1. Open a new terminal window and navigate to the frontend directory:
   ```bash
   cd path/to/your/frontend-folder

2. Install the dependencies: This reads the package.json file and downloads all required libraries to a local node_modules folder.
   ```Bash
   npm install
3. Once the installation is complete, start the Angular development server:
   ```Bash
   npm install
4. Open your web browser and navigate to http://localhost:4200/ to access the application's Graphical User Interface.
