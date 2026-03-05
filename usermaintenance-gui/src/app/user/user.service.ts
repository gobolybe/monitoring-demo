import {HttpClient, HttpParams} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {User} from './user.model';

@Injectable({ providedIn: 'root' })
export class UserService {
  private apiUrl = '/api/v1/user';

  constructor(private http: HttpClient) {}

  getUsers(name?: string): Observable<User[]> {
    let params = new HttpParams();
    if (name) {
      params = params.append('name', name);
    }
    return this.http.get<User[]>(`${this.apiUrl}/list`, { params });
  }

  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
