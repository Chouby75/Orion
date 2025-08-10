import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProfileUpdate } from '../models/profile';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  constructor(private http: HttpClient) {}

  private header = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('auth_token')}`,
  });

  getUserProfile(): Observable<any> {
    return this.http.get('http://localhost:3001/api/me', {
      headers: this.header,
    });
  }

  updateUserProfile(profileData: ProfileUpdate): Observable<any> {
    return this.http.put('http://localhost:3001/api/me', profileData, {
      headers: this.header,
    });
  }
}
