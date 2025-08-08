import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

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
    return this.http.get('http://localhost:3001/api/account/profile', {
      headers: this.header,
    });
  }

  updateUserProfile(profileData: any): Observable<any> {
    return this.http.put(
      'http://localhost:3001/api/account/profile',
      profileData,
      {
        headers: this.header,
      }
    );
  }
}
