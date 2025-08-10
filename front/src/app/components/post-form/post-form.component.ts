import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FeedService } from '../../services/feed.service'; // Adapte le chemin vers ton service

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.scss'],
})
export class PostFormComponent implements OnInit {
  public postForm: FormGroup;
  // TODO: Remplace ce tableau par un appel à ton service pour récupérer les thèmes
  public themes = [
    { id: 1, name: 'Jeux Vidéo' },
    { id: 2, name: 'Cinéma' },
    { id: 3, name: 'Musique' },
    { id: 4, name: 'Tech' },
  ];

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private articleService: FeedService
  ) {
    this.postForm = this.fb.group({
      theme: ['', [Validators.required]],
      titre: ['', [Validators.required, Validators.minLength(5)]],
      description: ['', [Validators.required, Validators.minLength(20)]],
    });
  }

  ngOnInit(): void {
    // Ici, tu pourrais appeler un service pour charger la liste des thèmes par exemple
  }

  public submit(): void {
    if (this.postForm.valid) {
      // Le formulaire est valide, on peut envoyer les données
      console.log('Données du formulaire :', this.postForm.value);

      // --- Voici comment tu appellerais ton service ---
      this.articleService.createPost(this.postForm.value).subscribe(
        (response) => {
          console.log('Article créé avec succès !', response);
          // On redirige l'utilisateur vers la liste des articles
          this.router.navigate(['/articles']);
        },
        (error) => {
          console.error("Erreur lors de la création de l'article", error);
        }
      );
    }
  }

  public back(): void {
    window.history.back();
  }
}
