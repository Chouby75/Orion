import { Component, OnDestroy, OnInit } from '@angular/core';
import { FeedService } from '../../services/feed.service';
import { PostSummary } from '../../models/post';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss'],
})
export class FeedComponent implements OnInit, OnDestroy {
  public articles: PostSummary[] = [];
  public sortDirection: 'desc' | 'asc' = 'desc';
  private feed?: Subscription;

  constructor(private articleService: FeedService, private router: Router) {}

  ngOnInit(): void {
    this.loadArticles();
  }

  ngOnDestroy(): void {
    this.feed?.unsubscribe();
  }

  private loadArticles(): void {
    this.feed = this.articleService.getPosts().subscribe(
      (data: PostSummary[]) => {
        this.articles = data;
        this.sortArticles();
      },
      (error) => {
        console.error('Erreur lors de la récupération des articles:', error);
      }
    );
  }

  public toggleSort(): void {
    this.sortDirection = this.sortDirection === 'desc' ? 'asc' : 'desc';
    this.sortArticles();
  }

  private sortArticles(): void {
    this.articles.sort((a, b) => {
      const dateA = new Date(a.createdAt).getTime();
      const dateB = new Date(b.createdAt).getTime();

      if (this.sortDirection === 'desc') {
        return dateB - dateA; // Plus récent en premier
      } else {
        return dateA - dateB; // Plus ancien en premier
      }
    });
  }

  public goToArticle(articleId: number): void {
    this.router.navigate([`main/feed/${articleId}`]);
  }

  createPost(): void {
    this.router.navigate(['main/feed/new']);
  }
}
