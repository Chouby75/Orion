import { Component, OnInit, OnDestroy } from '@angular/core';
import { Location } from '@angular/common'; // N'oublie pas d'importer ça
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service'; // Assurez-vous que le chemin est
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  constructor(
    private location: Location,
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  RegisterForm!: FormGroup;
  register!: any;

  ngOnInit(): void {
    this.RegisterForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(3)]],
    });
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
    }
  }

  OnDestroy(): void {
    this.register?.unsubscribe();
  }
}
