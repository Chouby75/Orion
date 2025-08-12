export interface PostSummary {
  id: number;
  title: string;
  content: string;
  author: string;
  createdAt: Date;
}

export class PostForm {
  title: string;
  content: string;
  topics: string[];

  constructor(title: string = '', content: string = '', topics: string[] = []) {
    this.title = title;
    this.content = content;
    this.topics = topics;
  }
}
