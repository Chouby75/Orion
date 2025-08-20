import { Component, OnInit } from '@angular/core'; // 1. On importe OnInit
import { TopicService } from '../../services/topic.service'; // 2. IMPORTE TON SERVICE (ajuste le chemin)
import { Topic } from '../../models/topic'; // 3. IMPORTE TON INTERFACE (ajuste le chemin)
import { Router } from '@angular/router';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.scss'],
})
export class TopicComponent implements OnInit {
  public topics: Topic[] = [];

  constructor(private topicService: TopicService, private router: Router) {}

  ngOnInit(): void {
    this.topicService.getTopics().subscribe(
      (data: Topic[]) => {
        this.topics = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des topics:', error);
      }
    );
  }

  subscribe(topicId: number): void {
    this.topicService.subscribeToTopic(topicId).subscribe(
      (response) => {
        this.ngOnInit();
      },
      (error) => {
        console.error('Subscription failed:', error);
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
}
