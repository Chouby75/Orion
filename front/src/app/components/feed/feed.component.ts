import { Component, OnInit } from '@angular/core'; // 1. On importe OnInit
import { FeedService } from '../../services/feed.service'; // 2. IMPORTE TON SERVICE (ajuste le chemin)
import { PostSummary } from '../../models/post'; // 3. IMPORTE TON INTERFACE (ajuste le chemin)
import { Router } from '@angular/router';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss'],
})
export class FeedComponent implements OnInit {
  public articles: PostSummary[] = [];

  constructor(private articleService: FeedService, private router: Router) {}

  ngOnInit(): void {
    this.articleService.getPosts().subscribe(
      (data: PostSummary[]) => {
        this.articles = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des articles:', error);
      }
    );
  }

  createPost(): void {
    this.router.navigate(['feed/new']);
  }
}
