package com.example.demo.service;

import com.example.demo.model.Notebook;

import java.util.List;
import java.util.Optional;

public interface NotebookService {

    Notebook save(Notebook notebook);

    void deleteById(Long id);

    Optional<Notebook> findById(Long id);

    List<Notebook> findAllByUserId(Long userId);
}
