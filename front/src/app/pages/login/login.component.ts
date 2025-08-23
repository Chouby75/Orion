import { Component, OnInit, OnDestroy } from '@angular/core';
import { Location } from '@angular/common'; // N'oublie pas d'importer ça
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service'; // Assurez-vous que le chemin est correct
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit, OnDestroy {
  constructor(
    private location: Location,
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  loginForm!: FormGroup;
  login?: Subscription;
  loginError: string = ''; // Pour stocker le message d'erreur
  isLoading: boolean = false; // Pour gérer l'état de chargement

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(3)]],
    });
  }

  goBack(): void {
    this.location.back();
  }

  onSubmit(): void {
    // Réinitialiser le message d'erreur
    this.loginError = '';

    if (this.loginForm.valid) {
      this.isLoading = true; // Démarrer le chargement
      const credentials = this.loginForm.value;

      this.login = this.authService.login(credentials).subscribe(
        (response) => {
          this.isLoading = false;
          this.router.navigate(['/main/feed']);
        },
        (error) => {
          this.isLoading = false;
          console.error('Login failed', error);

          // Gestion des différents types d'erreurs
          if (error?.error?.token) {
            this.loginError = error.error.token;
          } else if (error?.error?.message) {
            this.loginError = error.error.message;
          } else if (error?.message) {
            this.loginError = error.message;
          } else {
            this.loginError =
              'Une erreur est survenue lors de la connexion. Veuillez réessayer.';
          }
        }
      );
    } else {
      console.log('Form is invalid');
      this.markFormGroupTouched();
    }
  }

  // Méthode pour marquer tous les champs comme touchés (pour afficher les erreurs de validation)
  markFormGroupTouched(): void {
    Object.keys(this.loginForm.controls).forEach((key) => {
      const control = this.loginForm.get(key);
      if (control) {
        control.markAsTouched();
      }
    });
  }

  // Méthode pour obtenir les messages d'erreur d'un champ spécifique
  getFieldErrorMessage(fieldName: string): string {
    const field = this.loginForm.get(fieldName);

    if (field?.errors && field.touched) {
      if (field.errors['required']) {
        return `Le ${
          fieldName === 'username' ? "nom d'utilisateur" : 'mot de passe'
        } est requis`;
      }
      if (field.errors['minlength']) {
        return `Le ${
          fieldName === 'username' ? "nom d'utilisateur" : 'mot de passe'
        } doit contenir au moins 3 caractères`;
      }
    }

    return '';
  }

  // Méthode pour vérifier si un champ a des erreurs
  hasFieldError(fieldName: string): boolean {
    const field = this.loginForm.get(fieldName);
    return !!(field?.errors && field.touched);
  }

  // Méthode pour effacer le message d'erreur de connexion
  clearLoginError(): void {
    this.loginError = '';
  }

  ngOnDestroy(): void {
    this.login?.unsubscribe();
  }
}
