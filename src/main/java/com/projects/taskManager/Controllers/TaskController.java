package com.projects.taskManager.Controllers;

import com.projects.taskManager.DTO.AddTaskDTO;
//import com.projects.taskManager.DTO.DeleteTaskDTO;
import com.projects.taskManager.DTO.ErrorResponseDTO;
import com.projects.taskManager.DTO.UpdateTaskDTO;
import com.projects.taskManager.Entities.TaskEntity;
import com.projects.taskManager.Services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        var tasks = taskService.getTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id){
        var task = taskService.getTaskById(id);
        if(task==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody AddTaskDTO body) throws ParseException {
        var newTask = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());

        return ResponseEntity.ok(newTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Integer id) {
        boolean isDeleted = taskService.deleteTask(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e) {
        if (e instanceof ParseException) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format"));
        }

        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body) throws ParseException{
        var task = taskService.updateTask(id , body.getDescription(), body.getDeadline(), body.getStatus());

        if(task==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

//    @DeleteMapping("/{id}")
//    public void deleteTask(@PathVariable("id") int id){
//        DeleteTaskDTO task = new DeleteTaskDTO(id);
//        taskService.deleteTask(task);
//    }

}
