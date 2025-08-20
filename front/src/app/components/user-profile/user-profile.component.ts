import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// IMPORTE TON SERVICE (ajuste le chemin selon ta structure)
import { AccountService } from '../../services/account.service';
// IMPORTE TES INTERFACES (ajuste les chemins selon ta structure)
import { Profile } from '../../models/profile';
import { Topic } from '../../models/topic';
import { TopicService } from '../../services/topic.service'; // IMPORTE TON SERVICE (ajuste le chemin)

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

  public userProfileForm: any = {
    username: '',
    email: '',
    password: '',
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
    // this.loadSubscribedTopics();
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
        }; // Ne pas afficher le mot de passe
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
        // Optionally, you can refresh the topics list or show a success message
      },
      (error) => {
        console.error('Unsubscription failed:', error);
      }
    );
  }

  /**
   * Met à jour le profil utilisateur
   */
  updateProfile(): void {
    if (this.isFormValid()) {
      this.isLoading = true;
      this.errorMessage = '';

      this.userService.updateUserProfile(this.userProfile).subscribe(
        (response) => {
          console.log('Profil mis à jour avec succès:', response);
          this.isLoading = false;
          // Recharger le profil pour avoir les données à jour
          this.loadUserProfile();
        },
        (error) => {
          console.error('Erreur lors de la mise à jour du profil:', error);
          this.errorMessage = 'Erreur lors de la mise à jour du profil';
          this.isLoading = false;
        }
      );
    } else {
      this.errorMessage = 'Veuillez remplir correctement tous les champs';
    }
  }

  /**
   * Vérifie si le formulaire est valide
   */
  private isFormValid(): boolean {
    return !!(
      this.userProfile.username?.trim() &&
      this.userProfile.email?.trim() &&
      this.isValidEmail(this.userProfile.email)
    );
  }

  /**
   * Vérifie si l'email est valide
   */
  private isValidEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
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
