package com.example.demo.service.impl;

import com.example.demo.db.NotebookRepository;
import com.example.demo.model.Notebook;
import com.example.demo.service.NotebookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NotebookServiceImpl implements NotebookService {
    private final NotebookRepository notebookRepository;

    public NotebookServiceImpl(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    @Override
    public Notebook save(Notebook notebook) {
        return notebookRepository.save(notebook);
    }

    @Override
    public void deleteById(Long id) {
        notebookRepository.deleteById(id);
    }

    @Override
    public Optional<Notebook> findById(Long id) {
        return notebookRepository.findById(id);
    }

    @Override
    public List<Notebook> findAllByUserId(Long userId) {
        return notebookRepository.findAllByUserId(userId);
    }
}
