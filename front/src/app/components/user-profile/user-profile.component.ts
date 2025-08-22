import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// IMPORTE TON SERVICE (ajuste le chemin selon ta structure)
import { AccountService } from '../../services/account.service';
// IMPORTE TES INTERFACES (ajuste les chemins selon ta structure)
import { Profile } from '../../models/profile';
import { Topic } from '../../models/topic';
import { TopicService } from '../../services/topic.service';
import { ProfileUpdate } from '../../models/profile';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss'],
})
export class UserProfileComponent implements OnInit {
  public userProfile: Profile = {
    id: 0,
    username: '',
    email: '',
    topics: [],
  };

  // Nouveau: objet séparé pour le formulaire
  public formData = {
    username: '',
    email: '',
    password: '',
  };

  // Garder une copie des données originales pour comparaison
  private originalProfile: Profile = {
    id: 0,
    username: '',
    email: '',
    topics: [],
  };

  public subscribedTopics: Topic[] = [];
  public isLoading: boolean = false;
  public errorMessage: string = '';

  constructor(
    private userService: AccountService,
    private router: Router,
    private topicService: TopicService
  ) {}

  ngOnInit(): void {
    this.loadUserProfile();
  }

  /**
   * Charge les informations du profil utilisateur actuel
   */
  loadUserProfile(): void {
    this.isLoading = true;
    this.userService.getUserProfile().subscribe(
      (data: Profile) => {
        this.userProfile = {
          id: data.id,
          username: data.username,
          email: data.email,
          topics: data.topics || [],
        };

        // Sauvegarder les données originales
        this.originalProfile = { ...this.userProfile };

        // Initialiser le formulaire avec les données actuelles
        this.formData = {
          username: data.username,
          email: data.email,
          password: '',
        };

        this.isLoading = false;
      },
      (error) => {
        console.error('Erreur lors de la récupération du profil:', error);
        this.errorMessage = 'Impossible de charger le profil utilisateur';
        this.isLoading = false;
      }
    );
  }

  unsubscribe(topicId: number): void {
    this.topicService.unsubscribeFromTopic(topicId).subscribe(
      (response) => {
        this.ngOnInit();
      },
      (error) => {
        console.error('Unsubscription failed:', error);
      }
    );
  }

  /**
   * Met à jour le profil utilisateur avec seulement les champs modifiés
   */
  updateProfile(): void {
    this.isLoading = true;
    this.errorMessage = '';

    // Construire l'objet ProfileUpdate avec seulement les champs modifiés
    const profileUpdate: ProfileUpdate = this.buildUpdateObject();

    // Vérifier s'il y a des changements
    if (Object.keys(profileUpdate).length === 0) {
      this.errorMessage = 'Aucune modification détectée';
      this.isLoading = false;
      return;
    }

    this.userService.updateUserProfile(profileUpdate).subscribe(
      (response) => {
        console.log('Profil mis à jour avec succès:', response);
        this.isLoading = false;
        // Recharger le profil pour avoir les données à jour
        this.loadUserProfile();
        // Réinitialiser le champ mot de passe
        this.formData.password = '';
      },
      (error) => {
        console.error('Erreur lors de la mise à jour du profil:', error);
        this.errorMessage = 'Erreur lors de la mise à jour du profil';
        this.isLoading = false;
      }
    );
  }

  /**
   * Construit l'objet ProfileUpdate avec seulement les champs modifiés
   */
  private buildUpdateObject(): ProfileUpdate {
    const updateObject: ProfileUpdate = {};

    // Vérifier si le username a changé
    if (this.formData.username.trim() !== this.originalProfile.username) {
      updateObject.username = this.formData.username.trim();
    }

    // Vérifier si l'email a changé
    if (this.formData.email.trim() !== this.originalProfile.email) {
      updateObject.email = this.formData.email.trim();
    }

    // Vérifier si un mot de passe a été saisi
    if (this.formData.password.trim() !== '') {
      updateObject.password = this.formData.password.trim();
    }

    return updateObject;
  }

  /**
   * Navigation vers les thèmes (si nécessaire)
   */
  navigateToTopics(): void {
    this.router.navigate(['/topics']);
  }

  /**
   * Navigation vers les articles (si nécessaire)
   */
  navigateToArticles(): void {
    this.router.navigate(['/articles']);
  }
}
