import {Component, inject, OnInit, signal} from '@angular/core';
import {FormsModule, NgForm} from '@angular/forms';
import {UserService} from './user/user.service';
import {User} from './user/user.model';

@Component({
  selector: 'app-root',
  imports: [
    FormsModule
  ],
  templateUrl: './app.html',
  styleUrl: './app.sass'
})
export class App implements OnInit {
  protected readonly title = signal('usermaintenance-gui');
  users: User[] = [];
  newUser: User = { name: '', email: '' };
  searchTerm: string = '';

  private userService: UserService = inject(UserService);

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getUsers().subscribe(data => this.users = data);
  }

  addUser(form: NgForm) {
    if (form.valid) {
      this.userService.addUser(this.newUser).subscribe(() => {
        this.loadUsers();
        this.newUser = { name: '', email: '' };
        form.resetForm(); // Itt ürítjük ki az űrlapot és a hibaüzeneteket
      });
    }
  }

  onSearch() {
    this.userService.getUsers(this.searchTerm).subscribe(data => {
      this.users = data;
    });
  }

  deleteUser(id: number) {
    this.userService.deleteUser(id).subscribe(() => this.loadUsers());
  }
}
