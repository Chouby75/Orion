import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Token } from '../models/token';
import { tap } from 'rxjs/operators';
import { ProfileUpdate } from '../models/profile';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(credentials: ProfileUpdate): Observable<Token> {
    return this.http
      .post<Token>('http://localhost:3001/api/auth/login', credentials)
      .pipe(
        tap((token: Token) => {
          localStorage.setItem('auth_token', token.token);
        })
      );
  }

  register(user: ProfileUpdate): Observable<Token> {
    return this.http
      .post<Token>('http://localhost:3001/api/auth/register', user)
      .pipe(
        tap((token: Token) => {
          localStorage.setItem('auth_token', token.token);
        })
      );
  }
}
