import {Component, OnInit} from '@angular/core';
import {Notebook} from "./model/notebook";
import {ApiService} from "../shared/api.service";
import {Note} from "./model/note";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {
  notebooks: Notebook[] = [];
  notes: Note[] = [];
  selectedNotebook: Notebook;
  searchText: string;

  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
    this.getAllNotebooks();
    this.getAllNotes();
  }

  public getAllNotebooks() {
    let userId = sessionStorage.getItem("userId");
    this.apiService.getAllNotebooks(userId).subscribe(
      res => {
        this.notebooks = res;
      },
      err => {
        alert("An error has been occurred while getting all notebooks")
      }
    );
  }

  getAllNotes() {
    let userId = sessionStorage.getItem("userId");
    this.apiService.getAllNotes(userId).subscribe(
      res => {
        this.notes = res;
      },
      err => {
        alert("An error has been occurred while getting notes")
      }
    )
  }

  createNotebook() {
    let newNotebook: Notebook = {
      userId: sessionStorage.getItem("userId"),
      name: "New notebook",
      id: null,
      nbNotes: 0
    }
    this.apiService.postNotebook(newNotebook).subscribe(
      res => {
        newNotebook.id = res.id;
        this.notebooks.push(newNotebook);
      },
      err => {
        alert("An error has been occurred while saving notebook")
      }
    )
  }

  updateNotebook(updateNotebook: Notebook) {
    updateNotebook.userId = sessionStorage.getItem("userId");
    this.apiService.postNotebook(updateNotebook,).subscribe(
      res => {

      },
      err => {
        alert("An error has been occurred while updating notebook")
      }
    )
  }

  deleteNotebook(notebook: Notebook) {
    if (confirm("Are you sure you want to delete the " + notebook.name + " ?"))
      this.apiService.deleteNotebook(notebook.id).subscribe(
        res => {
          let indexOfNotebook = this.notebooks.indexOf(notebook);
          this.notebooks.splice(indexOfNotebook, 1);
        },
        err => {
          alert("An error has been occurred while deleting notebook")
        }
      )
  }

  deleteNote(note: Note) {
    if (confirm("Are you sure you want to delete this note?")) {
      this.apiService.deleteNoteById(note.id).subscribe( //todo to change name of method deleteNoteById
        res => {
          let indexOfNote = this.notes.indexOf(note);
          this.notes.splice(indexOfNote, 1);
        },
        err => {
          alert("An error was occurred while deleting note with title " + note.title)
        }
      )
    }
  }

  createNote(notebookId: string) {
    let newNote: Note = {
      id: null,
      title: "New note",
      text: "Write some text here",
      lastModifiedOn: null,
      notebookId: notebookId
    }
    this.apiService.saveNote(newNote).subscribe(
      res => {
        newNote.id = res.id;
        this.notes.push(newNote);
      },
      err => {
        alert("An error has been occurred while saving note");
      }
    )
  }

  selectNotebook(notebook: Notebook) {
    this.selectedNotebook = notebook;
    this.apiService.getNotesByNotebook(notebook.id).subscribe(
      res => {
        this.notes = res;
      },
      err => {
        alert("An error occurred while getting notes for notebook " + notebook.name);
      }
    )
  }

  updateNote(updateNote: Note) {
    this.apiService.saveNote(updateNote).subscribe(
      res => {
      },
      err => {
        alert("An error occurred while updating note")
      }
    )
  }

  selectAllNotes() {
    this.selectedNotebook = null;
    this.getAllNotes();
  }
}
