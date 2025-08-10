import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  // La variable qui va nous dire si le menu mobile est ouvert ou non.
  // On l'initialise à 'false' car au début, le menu est fermé.
  isMenuOpen: boolean = false;

  constructor() {}

  ngOnInit(): void {}

  // Cette fonction est appelée quand on clique sur le bouton hamburger.
  // Elle inverse simplement la valeur de 'isMenuOpen'.
  // Si c'était 'false', ça devient 'true', et vice-versa.
  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }

  // Cette fonction est appelée quand on clique sur un lien dans le menu mobile.
  // Elle force la fermeture du menu pour que l'utilisateur voie la nouvelle page.
  closeMenu(): void {
    this.isMenuOpen = false;
  }
}
