import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TopicService {
  constructor(private http: HttpClient) {}

  private apiUrl = environment.apiUrl;

  private header = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('auth_token')}`,
  });

  private headerWithoutBody = new HttpHeaders({
    Authorization: `Bearer ${localStorage.getItem('auth_token')}`,
  });

  getTopics(): Observable<any> {
    return this.http.get(this.apiUrl + '/topics', {
      headers: this.header,
    });
  }

  subscribeToTopic(topicId: number): Observable<any> {
    return this.http.put(
      this.apiUrl + `/topics/subscribe?topicId=${topicId}`,
      null,
      {
        headers: this.headerWithoutBody,
      }
    );
  }

  unsubscribeFromTopic(topicId: number): Observable<any> {
    return this.http.put(
      this.apiUrl + `/topics/unsubscribe?topicId=${topicId}`,
      null,
      {
        headers: this.headerWithoutBody,
      }
    );
  }
}
