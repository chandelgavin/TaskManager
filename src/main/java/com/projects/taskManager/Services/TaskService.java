package com.projects.taskManager.Services;

import com.projects.taskManager.Entities.TaskEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.SplittableRandom;

@Service
public class TaskService{
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;
    private final SimpleDateFormat deadlineFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity addTask(String title, String description, String deadline) throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);

        Date parsedDate = deadlineFormatter.parse(deadline);
        String formattedDeadLine = deadlineFormatter.format(parsedDate);

        task.setDeadline(deadlineFormatter.parse(formattedDeadLine));
        task.setStatus(false);
        tasks.add(task);
        taskId++;

        return task;
    }
    public ArrayList<TaskEntity> getTasks(){
        return tasks;
    }

    public TaskEntity getTaskById(int id){
        for(TaskEntity task:tasks){
            if(task.getId()==id){
                return task;
            }
        }
        return null;
    }

    public TaskEntity updateTask(int id, String description, String deadline, Boolean status) throws ParseException{
        TaskEntity task = getTaskById(id);
        if(task==null){
            return null;
        }
        if(description!=null){
            task.setDescription(description);
        }
        if(deadline!=null){
            task.setDeadline(deadlineFormatter.parse(deadline));
        }
        if(status!=null){
            task.setStatus(status);
        }
        return task;
    }

    public boolean deleteTask(int id) {
        TaskEntity task = getTaskById(id);
        if (task != null) {
            tasks.remove(task);
            return true;
        }
        return false;
    }


}
