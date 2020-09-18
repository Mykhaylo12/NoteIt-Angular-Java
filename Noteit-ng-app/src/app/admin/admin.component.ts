import {Component, OnInit} from '@angular/core';
import {User} from "../notes/model/user";
import {ApiService} from "../shared/api.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  users: User[] = [];

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.getAllUsers();
  }

  delete(user: User) {
    this.apiService.deleteUser(user.id).subscribe(
      data => {
        let indexOfUser = this.users.indexOf(user);
        this.users.splice(indexOfUser, 1)
      },
      error => {
        alert("Error occurred while deleting user with id " + user.id)
      }
    )
  }

  private getAllUsers() {
    this.apiService.getAllUsers().subscribe(
      data => {
        this.users = data
      },
      err => {
        alert("Error occurred when while getting users ")
      }
    );

  }
}

export interface UserViewModel {
  userId: string;
  email: string;
  username: string;
}
