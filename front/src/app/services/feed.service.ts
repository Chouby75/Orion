import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PostForm } from '../models/post';

@Injectable({
  providedIn: 'root',
})
export class FeedService {
  constructor(private http: HttpClient) {}

  private header = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('auth_token')}`,
  });

  getPosts(): Observable<any> {
    return this.http.get('http://localhost:3001/api/feed', {
      headers: this.header,
    });
  }

  getPostById(id: number): Observable<any> {
    return this.http.get(`http://localhost:3001/api/feed/${id}`, {
      headers: this.header,
    });
  }

  createPost(post: PostForm): Observable<any> {
    return this.http.post('http://localhost:3001/api/feed', post, {
      headers: this.header,
    });
  }

  commentOnPost(postId: string, comment: { content: string }): Observable<any> {
    return this.http.post(
      `http://localhost:3001/api/feed/${postId}/comments`,
      comment,
      {
        headers: this.header,
      }
    );
  }
}
