import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NavigationComponent} from './navigation/navigation.component';
import {FeedbackComponent} from './feedback/feedback.component';
import {NotesComponent} from './notes/notes.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {RouterModule, Routes} from '@angular/router'
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NoteComponent} from './notes/note/note.component';
import {NoteTextFilterPipe} from './shared/note-text-filter.pipe';
import {RegistrationComponent} from "./registration/registration.component";

import {LoginComponent} from './login/login.component';
import {LogoutComponent} from './logout/logout.component';
import {AuthGuardServiceUser} from "./service/auth-guard.service";
import {authInterceptorProviders} from "./service/token-interceptor";
import {AdminComponent} from './admin/admin.component';
import {AuthGuardServiceAdmin} from "./service/auth-guard-admin.service";

const appRoutes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/login'},
  {path: 'login', component: LoginComponent},
  {path: 'notes', component: NotesComponent, canActivate: [AuthGuardServiceUser]},
  {path: 'registration', component: RegistrationComponent},
  {path: 'feedback', component: FeedbackComponent, canActivate: [AuthGuardServiceUser]},
  {path: 'admin', component: AdminComponent, canActivate: [AuthGuardServiceAdmin]},
  {path: 'logout', component: LogoutComponent,},
  {path: '**', component: NotFoundComponent},
];

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    FeedbackComponent,
    NotesComponent,
    NotFoundComponent,
    NoteComponent,
    NoteTextFilterPipe,
    RegistrationComponent,
    LoginComponent,
    LogoutComponent,
    AdminComponent
  ],
  imports: [
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes, {enableTracing: true}),
    BrowserModule,
  ],
  providers: [LoginComponent, LogoutComponent, authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}


