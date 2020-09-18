import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ApiService} from "../shared/api.service";
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  model: LoginViewModel = {
    userId: '',
    email: '',
    password: '',
    username: '',
  };

  public isLoggedIn = false;

  constructor(private router: Router,
              private apiService: ApiService,
              private authService: AuthService) {
  }

  ngOnInit() {
  }

  login(): any {
    if (this.model.email.length == 0 || this.model.password.length == 0) {
      alert("Email or password is empty");
      return false;
    }
    this.apiService.authenticate(this.model).subscribe(
      data => {
        this.authService.login(data.token, data.id, data.email, data.username, data.roles);
        if (sessionStorage.getItem("roles").includes("ROLE_USER")) {
          window.location.replace('notes');
        } else {
          window.location.replace('admin')
        }
      },
      error => {
        alert("Login or password is invalid");
      }
    );
  }

  isLogged(): boolean {
    return this.isLoggedIn;
  }
}

export interface LoginViewModel {
  userId: string;
  email: string;
  password: string;
  username: string;
}
