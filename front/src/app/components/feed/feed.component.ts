import { Component, OnInit } from '@angular/core';
import { FeedService } from '../../services/feed.service';
import { PostSummary } from '../../models/post';
import { Router } from '@angular/router';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss'],
})
export class FeedComponent implements OnInit {
  public articles: PostSummary[] = [];
  public sortDirection: 'desc' | 'asc' = 'desc'; // 'desc' = plus récent au plus ancien, 'asc' = plus ancien au plus récent

  constructor(private articleService: FeedService, private router: Router) {}

  ngOnInit(): void {
    this.loadArticles();
  }

  private loadArticles(): void {
    this.articleService.getPosts().subscribe(
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
