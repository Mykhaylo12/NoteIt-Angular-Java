import {Component, OnInit} from '@angular/core';
import {ApiService} from "../shared/api.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  model: RegistrationViewModel = {
    username: '',
    password: '',
    repeatPassword: '',
    email: ''
  };

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
  }

  registration() {
    if (this.model.password != this.model.repeatPassword) {
      alert("Fields password and repeat password are not the same")
      return false;
    }
    if (!this.model.email.includes("@")) {
      alert("Email need to contain @")
      return false;
    }
    if (this.model.password.length < 5) {
      alert("Password contains less then 5 characters")
      return false;
    }

    this.apiService.registration(this.model).subscribe(
      res => {
        window.location.replace('login');
      },
      error => {
        alert("This email already exist");
      }
    )
  }
}

export interface RegistrationViewModel {
  username: string,
  password: string,
  repeatPassword: string,
  email: string
}
