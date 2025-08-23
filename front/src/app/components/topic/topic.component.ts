import { Component, OnDestroy, OnInit } from '@angular/core'; // 1. On importe OnInit
import { TopicService } from '../../services/topic.service'; // 2. IMPORTE TON SERVICE (ajuste le chemin)
import { Topic } from '../../models/topic'; // 3. IMPORTE TON INTERFACE (ajuste le chemin)
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.scss'],
})
export class TopicComponent implements OnInit, OnDestroy {
  public topics: Topic[] = [];
  private topicsSub?: Subscription;
  private subscribeTopic?: Subscription;

  constructor(private topicService: TopicService, private router: Router) {}

  ngOnInit(): void {
    this.topicsSub = this.topicService.getTopics().subscribe(
      (data: Topic[]) => {
        this.topics = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des topics:', error);
      }
    );
  }

  ngOnDestroy(): void {
    this.topicsSub?.unsubscribe();
    this.subscribeTopic?.unsubscribe();
  }

  subscribe(topicId: number): void {
    this.subscribeTopic = this.topicService.subscribeToTopic(topicId).subscribe(
      (response) => {
        this.ngOnInit();
      },
      (error) => {
        console.error('Subscription failed:', error);
      }
    );
  }
}
