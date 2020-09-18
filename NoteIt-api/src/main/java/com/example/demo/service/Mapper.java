package com.example.demo.service;

import com.example.demo.api.viewmodel.NoteViewModel;
import com.example.demo.api.viewmodel.NotebookViewModel;
import com.example.demo.api.viewmodel.UserViewModel;
import com.example.demo.db.NotebookRepository;
import com.example.demo.model.Note;
import com.example.demo.model.Notebook;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {
    private final NotebookRepository notebookRepository;
    private final UserService userService;

    public Mapper(NotebookRepository notebookRepository, UserService userService) {
        this.notebookRepository = notebookRepository;
        this.userService = userService;
    }

    public NoteViewModel convertToNoteViewModel(Note entity) {
        var viewModel = new NoteViewModel();
        viewModel.setTitle(entity.getTitle());
        viewModel.setId(String.valueOf(entity.getId()));
        viewModel.setLastModifiedOn(entity.getLastModifiedOn());
        viewModel.setText(entity.getText());
        viewModel.setNotebookId(entity.getNotebook().getId());
        return viewModel;
    }

    public Note convertToNoteEntity(NoteViewModel viewModel) {
        Notebook notebook = this.notebookRepository.findById(viewModel.getNotebookId()).get();
        if (viewModel.getId() == null) {
            return new Note(viewModel.getTitle(), viewModel.getText(), notebook);
        }
        return new Note(Long.valueOf(viewModel.getId()), viewModel.getTitle(), viewModel.getText(), notebook);
    }

    public NotebookViewModel convertToNotebookViewModel(Notebook entity) {
        NotebookViewModel viewModel = new NotebookViewModel();
        viewModel.setId(String.valueOf(entity.getId()));
        viewModel.setName(entity.getName());
        viewModel.setNbNotes(entity.getNotes().size());
        return viewModel;
    }

    public Notebook convertToNotebookEntity(NotebookViewModel viewModel) {
        User user = userService.getUserById(Long.parseLong(viewModel.getUserId()));
        if (viewModel.getId() == null) {
            return new Notebook(viewModel.getName(), user);
        }
        return new Notebook(Long.parseLong(viewModel.getId()), viewModel.getName(), user);
    }

    public User convertToUser(UserViewModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
        user.setUsername(userModel.getUsername());
        return user;
    }

    public List<String> convertUserRolesToString(List<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toList());
    }

    public UserViewModel convertToUserViewModel(User user) {
        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setEmail(user.getEmail());
        userViewModel.setId(String.valueOf(user.getId()));
        userViewModel.setUsername(user.getUsername());
        return userViewModel;
    }
}
