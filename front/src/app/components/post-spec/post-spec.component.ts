import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FeedService } from 'src/app/services/feed.service';

@Component({
  selector: 'app-post-spec',
  templateUrl: './post-spec.component.html',
  styleUrls: ['./post-spec.component.scss'],
})
export class PostSpecComponent implements OnInit {
  public article: any = {};
  public commentForm: FormGroup;
  public isSubmitting = false;

  constructor(
    private articleService: FeedService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) {
    // Initialisation du formulaire de commentaire
    this.commentForm = this.fb.group({
      content: ['', [Validators.required, Validators.minLength(3)]],
    });
  }

  ngOnInit(): void {
    const articleId = this.route.snapshot.paramMap.get('id');
    if (!articleId) {
      console.error('Article ID is missing in the route parameters.');
      return;
    }

    this.loadArticle(articleId);
  }

  loadArticle(articleId: string): void {
    this.articleService.getPostById(articleId).subscribe(
      (data) => {
        this.article = data;
      },
      (error) => {
        console.error("Erreur lors de la récupération de l'article:", error);
      }
    );
  }

  onSubmitComment(): void {
    if (this.commentForm.valid && !this.isSubmitting) {
      this.isSubmitting = true;
      const articleId = this.route.snapshot.paramMap.get('id');

      const content = this.commentForm.get('content')?.value;
      const postId = articleId;
      if (!postId) {
        console.error('Post ID is missing in the route parameters.');
        this.isSubmitting = false;
        return;
      }

      this.articleService.commentOnPost(postId, content).subscribe(
        (response) => {
          this.loadArticle(articleId!);
          // Réinitialiser le formulaire
          this.commentForm.reset();
          this.isSubmitting = false;
        },
        (error) => {
          console.error('Erreur lors de la création du commentaire:', error);
          this.isSubmitting = false;
        }
      );
    }
  }

  goBack(): void {
    this.router.navigate(['/main/feed']);
  }

  // Getter pour faciliter l'accès au contrôle dans le template
  get commentContent() {
    return this.commentForm.get('content');
  }
}
