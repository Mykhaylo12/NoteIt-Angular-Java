package com.example.demo.service.impl;

import com.example.demo.db.NoteRepository;
import com.example.demo.model.Note;
import com.example.demo.model.Notebook;
import com.example.demo.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public List<Note> findAllByNotebook(Notebook notebook) {
        return noteRepository.findAllByNotebook(notebook);
    }

    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public List<Note> findAllByUserId(Long userId) {
        return noteRepository.findAllByUserId(userId);
    }
}
