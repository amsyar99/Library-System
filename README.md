### Running the Application

To successfully run the application, you must execute the provided **`batchScript.sh`** file. This script is responsible for setting up the necessary environment and launching the application.

Need to install:

- Java 17 in local environment.

- **Run the Script (Linux/MacOs)**:
Execute the script by running the following command:
    
    ```bash
    ./batchScript.sh
    ```
    
- Run the Script (Windows)
    
    If you are using windows please run it, in git bash.
    

# API Documentation

**Base URL : http://localhost:8080**

### **1. Register a New Book**

- **Endpoint**: `/api/books`
- **Method**: `POST`
- **Description**: Registers a new book in the system.
- **Request Body**:
    - **Content-Type**: `application/json`
    - **Example Request**:
        
        ```json
        
        {
          "title": "The Great Gatsby",
          "author": "F. Scott Fitzgerald",
          "isbn": "9780743273565"
        }
        ```
        
    - **Required Fields**:
        - `title`: (String) The title of the book.
        - `author`: (String) The author of the book.
        - `isbn`: (String) The International Standard Book Number (ISBN).
- **Response**:
    - **Status Code**: `201 Created`
    - **Response Body**:
        
        ```json
        
        {
          "id": 1,
          "title": "The Great Gatsby",
          "author": "F. Scott Fitzgerald",
          "isbn": "9780743273565"
        }
        ```
        

---

### **2. Get All Books**

- **Endpoint**: `/api/books`
- **Method**: `GET`
- **Description**: Retrieves the list of all books.
- **Response**:
    - **Status Code**: `200 OK`
    - **Response Body**:
        
        ```json
        
        [
          {
            "id": 1,
            "title": "The Great Gatsby",
            "author": "F. Scott Fitzgerald",
            "isbn": "9780743273565"
          },
          {
            "id": 2,
            "title": "1984",
            "author": "George Orwell",
            "isbn": "9780451524935"
          }
        ]
        ```
        

---

### **3. Borrow a Book**

- **Endpoint**: `/api/books/{bookId}/borrow`
- **Method**: `PUT`
- **Description**: Allows a user to borrow a book. This requires the book’s ID and the borrower’s ID.
- **Path Parameters**:
    - `bookId` (Long): The ID of the book to borrow.
- **Query Parameters**:
    - `borrowerId` (Long): The ID of the borrower.
- **Request Example**:
    - **URL**: `/api/books/1/borrow?borrowerId=2`
    - **Response**:
        - **Status Code**: `200 OK`
        - **Response Body**:
            
            ```json
            
            {
              "id": 1,
              "title": "The Great Gatsby",
              "author": "F. Scott Fitzgerald",
              "isbn": "9780743273565"
            }
            
            ```
            

---

### **4. Return a Book**

- **Endpoint**: `/api/books/{bookId}/return`
- **Method**: `PUT`
- **Description**: Allows a user to return a borrowed book. This requires the book’s ID.
- **Path Parameters**:
    - `bookId` (Long): The ID of the book to return.
- **Request Example**:
    - **URL**: `/api/books/1/return`
    - **Response**:
        - **Status Code**: `200 OK`
        - **Response Body**:
            
            ```json
            {
              "id": 1,
              "title": "The Great Gatsby",
              "author": "F. Scott Fitzgerald",
              "isbn": "9780743273565"
            }
            
            ```
            

---

### **5. Register a New Borrower**

- **Endpoint**: `/api/books/borrowers`
- **Method**: `POST`
- **Description**: Registers a new borrower in the system.
- **Request Body**:
    - **Content-Type**: `application/json`
    - **Example Request**:
        
        ```json
        
        {
          "name": "John Doe",
          "email": "john.doe@example.com",
        }
        
        ```
        
    - **Required Fields**:
        - `name`: (String) The name of the borrower.
        - `email`: (String) The email address of the borrower.
        - `address`: (String) The address of the borrower.
- **Response**:
    - **Status Code**: `201 Created`
    - **Response Body**:
        
        ```json
        {
          "id": 1,
          "name": "John Doe",
          "email": "john.doe@example.com",
        }
        
        ```
        

General note:

- Command:
    - Check log : docker logs -f <container_name>
    - Check Container Running: docker ps
    - Check Image created: docker images

### Assumptions Documentation:

- **Assumption 1**: The application will use **MySQL** as the database, as no specific database technology was mentioned.
- **Assumption 2**: The project uses Gradle as the build tool, since no information was provided regarding the build system.
- **Assumption 3**: The application will run in a **Dockerized** environment, as no other deployment details were provided.
