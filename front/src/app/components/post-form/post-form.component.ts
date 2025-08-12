import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FeedService } from '../../services/feed.service'; // Adapte le chemin vers ton service
import { TopicService } from 'src/app/services/topic.service';
import { Topic } from 'src/app/models/topic';

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.scss'],
})
export class PostFormComponent implements OnInit {
  public postForm: FormGroup;
  public themes: Topic[] = [];

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private articleService: FeedService,
    private topicService: TopicService
  ) {
    this.postForm = this.fb.group({
      title: ['', [Validators.required]],
      content: ['', [Validators.required]],
      topics: [[], [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.topicService.getTopics().subscribe(
      (data) => {
        this.themes = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des thèmes:', error);
      }
    );
  }

  onTopicChange(event: any, topicName: string) {
    const currentTopics = this.postForm.get('topics')?.value || [];

    if (event.target.checked) {
      currentTopics.push({ name: topicName });
    } else {
      const index = currentTopics.findIndex(
        (topic: any) => topic.name === topicName
      );
      if (index > -1) {
        currentTopics.splice(index, 1);
      }
    }

    this.postForm.patchValue({ topics: currentTopics });
  }

  public submit(): void {
    if (this.postForm.valid) {
      const formData = this.postForm.value;
      const postData = {
        title: formData.title,
        content: formData.content,
        topics: formData.topics.map((topicName: string) => ({
          name: topicName,
        })),
      };

      console.log("Données envoyées à l'API :", postData);

      this.articleService.createPost(postData).subscribe(
        (response) => {
          this.router.navigate(['main/feed']); // Changez pour votre route correcte
        },
        (error) => {
          console.error("Erreur lors de la création de l'article", error);
        }
      );
    }
  }

  public back(): void {
    window.history.back();
  }
}
