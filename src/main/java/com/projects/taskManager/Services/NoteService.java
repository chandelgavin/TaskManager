package com.projects.taskManager.Services;

import com.projects.taskManager.Entities.NoteEntity;
import com.projects.taskManager.Entities.TaskEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NoteService {
    private TaskService taskService;

    public NoteService(TaskService taskService) {
        this.taskService = taskService;
    }

    private HashMap<Integer, TaskNotesHolder> taskNotesHolderMap = new HashMap<>();

    static class TaskNotesHolder{
        protected int noteId = 1;
        protected ArrayList<NoteEntity> notes = new ArrayList<>();
    }

    public List<NoteEntity> getNotesForTask(int taskId){
        TaskEntity task = taskService.getTaskById(taskId);
        if(task==null){
            return null;
        }
        if(taskNotesHolderMap.get(taskId)==null){
            taskNotesHolderMap.put(taskId, new TaskNotesHolder());
        }

        return taskNotesHolderMap.get(taskId).notes;
    }

    public NoteEntity getNoteById(int taskId, int noteId){
        NoteEntity note = taskNotesHolderMap.get(taskId).notes.get(noteId);
        if(note!=null){
            return note;
        }
        return null;
    }

    public NoteEntity addNote(int taskId, String title, String body){
        TaskEntity task = taskService.getTaskById(taskId);

        if(task==null){
            return null;
        }
        if(taskNotesHolderMap.get(taskId)==null){
            taskNotesHolderMap.put(taskId, new TaskNotesHolder());
        }

        TaskNotesHolder taskNotesHolder = taskNotesHolderMap.get(taskId);
        NoteEntity note = new NoteEntity();
        note.setId(taskNotesHolder.noteId);
        note.setTitle(title);
        note.setBody(body);
        taskNotesHolder.notes.add(note);
        taskNotesHolder.noteId++;
        return note;
    }

    public boolean deleteNote(int taskId, int noteId) {
        NoteEntity note = getNoteById(taskId, noteId);
        if (note != null) {
            taskNotesHolderMap.get(taskId).notes.remove(noteId);
            return true;
        }
        return false;
    }
}
