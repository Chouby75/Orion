import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProfileUpdate } from '../models/profile';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  constructor(private http: HttpClient) {}

  private apiUrl = environment.apiUrl;

  private header = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('auth_token')}`,
  });

  getUserProfile(): Observable<any> {
    return this.http.get(this.apiUrl + '/me', {
      headers: this.header,
    });
  }

  updateUserProfile(profileData: ProfileUpdate): Observable<any> {
    return this.http.put(this.apiUrl + '/me', profileData, {
      headers: this.header,
    });
  }
}
