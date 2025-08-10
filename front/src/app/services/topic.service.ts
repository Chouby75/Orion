import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TopicService {
  constructor(private http: HttpClient) {}

  private header = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('auth_token')}`,
  });

  getTopics(): Observable<any> {
    return this.http.get('http://localhost:3001/api/topics', {
      headers: this.header,
    });
  }

  subscribeToTopic(topicId: number, userId: number): Observable<any> {
    return this.http.post(
      `http://localhost:3001/api/topics/subscribe?topicId=${topicId}&&userId=${userId}`,
      {
        headers: this.header,
      }
    );
  }

  unsubscribeFromTopic(topicId: number, userId: number): Observable<any> {
    return this.http.post(
      `http://localhost:3001/api/topics/unsubscribe?topicId=${topicId}&&userId=${userId}`,
      {
        headers: this.header,
      }
    );
  }
}
