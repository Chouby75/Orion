import { Component, OnInit, OnDestroy } from '@angular/core';
import { Location } from '@angular/common'; // N'oublie pas d'importer ça
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { AuthService } from '../../services/auth.service'; // Assurez-vous que le chemin est correct
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit, OnDestroy {
  constructor(
    private location: Location,
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  RegisterForm!: FormGroup;
  register?: Subscription;

  ngOnInit(): void {
    this.RegisterForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, this.passwordValidator]],
    });
  }

  // Validateur personnalisé pour le mot de passe
  passwordValidator(control: AbstractControl): ValidationErrors | null {
    const password = control.value;

    if (!password) {
      return null; // Laissez le Validators.required gérer les champs vides
    }

    const errors: ValidationErrors = {};

    // Vérification de la longueur minimale (8 caractères)
    if (password.length < 8) {
      errors['minLength'] = {
        actualLength: password.length,
        requiredLength: 8,
      };
    }

    // Vérification de la présence d'au moins un chiffre
    if (!/\d/.test(password)) {
      errors['requiresDigit'] = true;
    }

    // Vérification de la présence d'au moins une lettre minuscule
    if (!/[a-z]/.test(password)) {
      errors['requiresLowercase'] = true;
    }

    // Vérification de la présence d'au moins une lettre majuscule
    if (!/[A-Z]/.test(password)) {
      errors['requiresUppercase'] = true;
    }

    // Vérification de la présence d'au moins un caractère spécial
    if (!/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~`]/.test(password)) {
      errors['requiresSpecialChar'] = true;
    }

    // Retourner null si aucune erreur, sinon retourner l'objet des erreurs
    return Object.keys(errors).length === 0 ? null : errors;
  }

  goBack(): void {
    this.location.back();
  }

  onSubmit(): void {
    if (this.RegisterForm.valid) {
      const credentials = this.RegisterForm.value;
      this.register = this.authService.register(credentials).subscribe(
        (response) => {
          this.router.navigate(['/main/feed']);
        },
        (error) => {
          console.error('Register failed', error);
        }
      );
    } else {
      console.log('Form is invalid');
      // Afficher les erreurs de validation du mot de passe
      const passwordErrors = this.RegisterForm.get('password')?.errors;
      if (passwordErrors) {
        console.log('Erreurs de mot de passe:', passwordErrors);
      }
    }
  }

  // Méthode pour obtenir les messages d'erreur du mot de passe
  getPasswordErrorMessage(): string[] {
    const passwordControl = this.RegisterForm.get('password');
    const errors: string[] = [];

    if (passwordControl?.errors && passwordControl.touched) {
      if (passwordControl.errors['required']) {
        errors.push('Le mot de passe est requis');
      }
      if (passwordControl.errors['minLength']) {
        errors.push('Le mot de passe doit contenir au moins 8 caractères');
      }
      if (passwordControl.errors['requiresDigit']) {
        errors.push('Le mot de passe doit contenir au moins un chiffre');
      }
      if (passwordControl.errors['requiresLowercase']) {
        errors.push(
          'Le mot de passe doit contenir au moins une lettre minuscule'
        );
      }
      if (passwordControl.errors['requiresUppercase']) {
        errors.push(
          'Le mot de passe doit contenir au moins une lettre majuscule'
        );
      }
      if (passwordControl.errors['requiresSpecialChar']) {
        errors.push(
          'Le mot de passe doit contenir au moins un caractère spécial'
        );
      }
    }

    return errors;
  }

  ngOnDestroy(): void {
    this.register?.unsubscribe();
  }
}
