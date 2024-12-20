package com.projects.taskManager.Controllers;
import com.projects.taskManager.DTO.AddNoteToTaskDTO;
import com.projects.taskManager.DTO.CreateNoteDTO;
import com.projects.taskManager.Entities.NoteEntity;
import com.projects.taskManager.Services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId){
        var notes = noteService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<AddNoteToTaskDTO> addNote(@PathVariable("taskId") Integer taskId, @RequestBody CreateNoteDTO body){
        var note = noteService.addNote(taskId, body.getTitle(), body.getBody());

        return ResponseEntity.ok(new AddNoteToTaskDTO(taskId, note));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteANote(@PathVariable("taskId") Integer taskId, @PathVariable("noteId") Integer noteId){
        boolean isDeleted = noteService.deleteNote(taskId,noteId);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}

