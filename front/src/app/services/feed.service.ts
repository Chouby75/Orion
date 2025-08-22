import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PostForm } from '../models/post';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class FeedService {
  constructor(private http: HttpClient) {}

  private apiUrl = environment.apiUrl;

  private header = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('auth_token')}`,
  });

  getPosts(): Observable<any> {
    return this.http.get(this.apiUrl + '/feed', {
      headers: this.header,
    });
  }

  getPostById(id: string): Observable<any> {
    return this.http.get(this.apiUrl + `/feed/${id}`, {
      headers: this.header,
    });
  }

  createPost(post: PostForm): Observable<any> {
    return this.http.post(this.apiUrl + '/feed', post, {
      headers: this.header,
    });
  }

  commentOnPost(postId: string, comment: { content: string }): Observable<any> {
    return this.http.post(
      this.apiUrl + `/feed/${postId}/comment`,
      { content: comment },
      {
        headers: this.header,
      }
    );
  }
}
