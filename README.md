# TaskManager
This is a Task Manager API built using the Spring Boot Framework. It supports basic CRUD operations for managing tasks and notes associated with tasks.

## Features

## Task Management

 Create a Task: Add a new task with a title, description, and deadline.

 Get All Tasks: Retrieve the list of all tasks.

 Get Task by ID: Fetch a specific task by its ID.

 Update a Task: Modify a task's description, deadline, or status.

 Delete a Task: Remove a task from the system.

### Note Management

Add Note to Task: Attach a note to a specific task.

Get All Notes for a Task: Retrieve all notes related to a task.

Delete a Note for a Task: Remove a specific note from a task.

### Technologies Used

 Java 17

 Spring Boot 3.4.0

 Maven

 H2 Database (for testing)

 Postman (for API testing)

## Installation and Setup

- Build the Project: mvn clean install

- Run the Application: mvn spring-boot:run

## Access API Endpoints:
Base URL: http://localhost:6969

## API Endpoints

### Task Endpoints:

`POST /tasks`  - Create a new task

`GET /tasks` - Get all tasks

`GET /tasks/{id}` - Get a task by ID

`PATCH /tasks/{id}` - Update a task

`DELETE /tasks/{id}` - Delete a task

### Note Endpoints:
`POST /tasks/{taskId}/notes` - Add a note to a task

`GET /tasks/{taskId}/notes` - Get all notes for a task

`DELETE /tasks/{taskId}/notes/{noteId}` - Delete a note from a task

## Example Requests

### Create a Task (POST /tasks)

{


    "title": "Complete Project",
    
    "description": "Finish task manager project by deadline",
    
    "deadline": "2024-12-31"

    
}

### Add a Note (POST /tasks/1/notes)

{


    "title": "Reminder",
    
    "body": "Review the project documentation."
    
}


