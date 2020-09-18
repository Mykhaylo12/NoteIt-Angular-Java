package com.example.demo.service;

import com.example.demo.model.Note;
import com.example.demo.model.Notebook;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Optional<Note> findById(Long id);

    List<Note> findAllByNotebook(Notebook notebook);

    Note save(Note noteEntity);

    void deleteById(Long id);

    List<Note> findAllByUserId(Long parseLong);
}

