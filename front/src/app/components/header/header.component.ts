import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  // La variable qui va nous dire si le menu mobile est ouvert ou non.
  // On l'initialise à 'false' car au début, le menu est fermé.
  isMenuOpen: boolean = false;

  constructor(private router: Router) {}

  ngOnInit(): void {}

  get isProfileActive(): boolean {
    return this.router.url.includes('/profile');
  }

  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }

  closeMenu(): void {
    this.isMenuOpen = false;
  }

  goToFeed(): void {
    this.closeMenu();
    this.router.navigate(['main/feed']);
  }

  goToTopics(): void {
    this.closeMenu();
    this.router.navigate(['main/topics']);
  }

  goToProfile(): void {
    this.closeMenu();
    this.router.navigate(['main/profile']);
  }

  logout(): void {
    localStorage.removeItem('auth_token');
    this.router.navigate(['']);
  }
}
