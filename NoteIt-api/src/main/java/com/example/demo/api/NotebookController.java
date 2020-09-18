package com.example.demo.api;

import com.example.demo.api.viewmodel.NotebookViewModel;
import com.example.demo.model.Notebook;
import com.example.demo.service.Mapper;
import com.example.demo.service.impl.NotebookServiceImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notebooks")
@CrossOrigin
public class NotebookController {
    private final NotebookServiceImpl notebookService;
    private final Mapper mapper;

    public NotebookController(NotebookServiceImpl notebookService, Mapper mapper) {
        this.notebookService = notebookService;
        this.mapper = mapper;
    }

    @GetMapping("/all/{id}")
    public List<NotebookViewModel> allNotebooksByUserId(@PathVariable String id) {
        List<Notebook> allByUserId = notebookService.findAllByUserId(Long.parseLong(id));
        return allByUserId
                .stream()
                .map(mapper::convertToNotebookViewModel)
                .collect(Collectors.toList());
    }

    @PostMapping
    public NotebookViewModel save(@RequestBody NotebookViewModel notebookViewModel,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        Notebook notebook = mapper.convertToNotebookEntity(notebookViewModel);
        Notebook updatedNotebook = notebookService.save(notebook);
        return mapper.convertToNotebookViewModel(updatedNotebook);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.notebookService.deleteById(Long.parseLong(id));
    }
}