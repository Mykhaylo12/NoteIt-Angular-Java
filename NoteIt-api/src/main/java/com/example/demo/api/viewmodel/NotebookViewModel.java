package com.example.demo.api.viewmodel;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NotebookViewModel {
    private String id;
    @NotNull
    private String name;
    private int nbNotes;
    private String userId;
}