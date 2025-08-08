import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(credentials: { username: string; password: string }): Observable<any> {
    const response: Observable<any> = this.http.post(
      'http://localhost:3001/api/auth/login',
      credentials
    );
    // localStorage.setItem('auth_token', response.token);
    return response;
  }

  register(user: {
    username: string;
    email: string;
    password: string;
  }): Observable<any> {
    const response: Observable<any> = this.http.post(
      'http://localhost:3001/api/auth/register',
      user
    );
    // localStorage.setItem('auth_token', response.token);
    return response;
  }
}
