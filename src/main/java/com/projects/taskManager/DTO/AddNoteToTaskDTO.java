package com.projects.taskManager.DTO;

import com.projects.taskManager.Entities.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class AddNoteToTaskDTO {
    private Integer taskId;
    private NoteEntity note;

    public Integer getTaskId() {
        return taskId;
    }

    public NoteEntity getNote() {
        return note;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public void setNote(NoteEntity note) {
        this.note = note;
    }

    public AddNoteToTaskDTO(Integer taskId, NoteEntity note) {
        this.taskId = taskId;
        this.note = note;
    }
}
