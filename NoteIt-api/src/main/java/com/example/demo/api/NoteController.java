package com.example.demo.api;

import com.example.demo.api.viewmodel.NoteViewModel;
import com.example.demo.model.Note;
import com.example.demo.model.Notebook;
import com.example.demo.service.Mapper;
import com.example.demo.service.impl.NoteServiceImpl;
import com.example.demo.service.impl.NotebookServiceImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController {
    private final NoteServiceImpl noteService;
    private final NotebookServiceImpl notebookService;
    private final Mapper mapper;

    public NoteController(NoteServiceImpl noteService, NotebookServiceImpl notebookService, Mapper mapper) {
        this.noteService = noteService;
        this.notebookService = notebookService;
        this.mapper = mapper;
    }

    @GetMapping("/all/{id}")
    public List<NoteViewModel> getAllNotesByUser(@PathVariable String id) {
        List<Note> notes = noteService.findAllByUserId(Long.parseLong(id));
        return notes.stream()
                .map(mapper::convertToNoteViewModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/byId/{id}")
    public NoteViewModel getNoteById(@PathVariable String id) {
        Note note = noteService.findById(Long.parseLong(id)).orElse(null);
        if (note == null) {
            throw new EntityNotFoundException();
        }
        return mapper.convertToNoteViewModel(note);
    }

    @GetMapping("/byNotebook/{notebookId}")
    public List<NoteViewModel> getNotesByNotebook(@PathVariable String notebookId) {
        List<Note> notes = new ArrayList<>();
        Optional<Notebook> notebook = notebookService.findById(Long.parseLong(notebookId));
        if (notebook.isPresent()) {
            notes = this.noteService.findAllByNotebook(notebook.get());
        }
        return notes.stream()
                .map(mapper::convertToNoteViewModel)
                .collect(Collectors.toList());
    }

    @PostMapping
    public NoteViewModel save(@RequestBody NoteViewModel noteCreateViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        Note mappedNote = mapper.convertToNoteEntity(noteCreateViewModel);
        Note savedNote = noteService.save(mappedNote);
        noteCreateViewModel.setId(String.valueOf(savedNote.getId()));
        return noteCreateViewModel;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        noteService.deleteById(Long.parseLong(id));
    }
}