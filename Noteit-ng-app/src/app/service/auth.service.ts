import {Injectable} from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn: boolean = false;
  private isAdmin: boolean = false;
  private isUser: boolean = false;

  constructor(private router: Router) {
  }

  get isUserIn() {
    if (sessionStorage.getItem("roles").includes("ROLE_USER") == null) {
      return false;
    }
    return sessionStorage.getItem("roles").includes("ROLE_USER");
  }

  get isAdminIn() {
    if (sessionStorage.getItem("roles").includes("ROLE_ADMIN") == null) {
      return false;
    }
    return sessionStorage.getItem("roles").includes("ROLE_ADMIN");
  }

  get isLoggedIn() {
    return this.loggedIn;
  }

  login(token: string, id: string, email: string, username: string, roles: any) {
    this.loggedIn = true;
    sessionStorage.setItem("email", email)
    sessionStorage.setItem("token", token);
    sessionStorage.setItem("isLogged", String(true));
    sessionStorage.setItem("userId", id)
    sessionStorage.setItem("roles", roles)
    sessionStorage.setItem("username", username)

  }

  logout() {
    sessionStorage.clear()
    this.loggedIn = false;
  }

  isUserLoggedIn() {
    return sessionStorage.getItem("isLogged");
  }
}





