import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Notebook} from "../notes/model/notebook";
import {FeedbackViewModel} from "../feedback/feedback.component";
import {Note} from "../notes/model/note";
import {User} from "../notes/model/user";
import {RegistrationViewModel} from "../registration/registration.component";
import {LoginViewModel} from "../login/login.component";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private BASE_URL = "http://localhost:8083/api";
  private ALL_NOTEBOOKS_URL = `${this.BASE_URL}/notebooks/all\/`;
  private SEND_FEEDBACK_URL = `${this.BASE_URL}/feedback`;
  private SAVE_UPDATE_NOTEBOOK_URL = `${this.BASE_URL}/notebooks`;
  private DELETE_NOTEBOOK_URL = `${this.BASE_URL}/notebooks\/`;
  private ALL_NOTES_URL = `${this.BASE_URL}/notes/all\/`;
  private NOTES_BY_NOTEBOOK_URL = `${this.BASE_URL}/notes/byNotebook\/`;
  private SAVE_UPDATE_NOTE_URL = `${this.BASE_URL}/notes`;
  private DELETE_NOTE_URL = `${this.BASE_URL}/notes\/`;
  private REGISTRATION_URL = `${this.BASE_URL}/registration`;
  private LOGIN_URL = `${this.BASE_URL}/login`;
  private GET_ALL_USERS = `${this.BASE_URL}/user`;
  private DELETE_USER_URL = `${this.BASE_URL}/user\/`;

  constructor(private http: HttpClient) {
  }


  authenticate(loginViewModel: LoginViewModel): Observable<User> {
    return this.http.post<User>(this.LOGIN_URL, loginViewModel);
  }

  registration(registrationModel: RegistrationViewModel): Observable<User> {
    return this.http.post<User>(this.REGISTRATION_URL, registrationModel);
  }

  getAllNotebooks(userId: string): Observable<Notebook[]> {
    return this.http.get<Notebook[]>(this.ALL_NOTEBOOKS_URL + userId);
  }

  postFeedback(feedback: FeedbackViewModel): Observable<any> {
    return this.http.post(this.SEND_FEEDBACK_URL, feedback);
  }

  postNotebook(notebook: Notebook): Observable<Notebook> {
    return this.http.post<Notebook>(this.SAVE_UPDATE_NOTEBOOK_URL, notebook);
  }

  deleteNotebook(id: string): Observable<any> {
    return this.http.delete(this.DELETE_NOTEBOOK_URL + id);
  }

  getAllNotes(userId: string): Observable<Note[]> {
    return this.http.get<Note[]>(this.ALL_NOTES_URL + userId);
  }

  getNotesByNotebook(notebookId: string): Observable<Note[]> {
    return this.http.get<Note[]>(this.NOTES_BY_NOTEBOOK_URL + notebookId);
  }

  saveNote(newNote: Note): Observable<Note> {
    return this.http.post<Note>(this.SAVE_UPDATE_NOTE_URL, newNote);
  }

  deleteNoteById(id: string) {
    return this.http.delete(this.DELETE_NOTE_URL + id);
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.GET_ALL_USERS);
  }

  deleteUser(userId: string) {
    return this.http.delete(this.DELETE_USER_URL + userId);
  }
}
