import { Component } from '@angular/core';
import { Location } from '@angular/common'; // N'oublie pas d'importer ça

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  constructor(private location: Location) {}

  goBack(): void {
    this.location.back(); // Cette fonction fait revenir à la page précédente
  }
}
