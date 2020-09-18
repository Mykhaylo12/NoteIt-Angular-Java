package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    private Notebook notebook;
    private Date lastDateModification;

    public Note() {
        this.lastDateModification = new Date();
    }

    public Note(String title, String text, Notebook notebook) {
        this();
        this.title = title;
        this.text = text;
        this.notebook = notebook;
    }

    public Note(Long id,String title, String text, Notebook notebook) {
        this();
        this.id=id;
        this.title = title;
        this.text = text;
        this.notebook = notebook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

//    public void setTitle(String title) {
//        this.title = title;
//    }

    public String getText() {
        return text;
    }

//    public void setText(String text) {
//        this.text = text;
//    }

    public Notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

//    public Date getLastDateModification() {
//        return lastDateModification;
//    }

    public Date getLastModifiedOn() {
        return lastDateModification;
    }

//    public void setLastDateModification(Date lastDateModification) {
//        this.lastDateModification = lastDateModification;
//    }
}
