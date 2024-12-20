package com.projects.taskManager.DTO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateNoteDTO {
    private String title;
    private String body;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
