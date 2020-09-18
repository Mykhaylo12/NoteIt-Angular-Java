package com.example.demo.api.viewmodel;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class NoteViewModel {
    private String id;
    @NotNull
    @Min(3)
    private String title;
    @NotNull
    private String text;
    @NotNull
    private Long notebookId;

    private Date lastModifiedOn;

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }
}