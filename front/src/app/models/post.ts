export interface PostSummary {
  id: number;
  title: string;
  content: string;
  author: string;
  createdAt: Date;
}

export interface PostForm {
  title: string;
  content: string;
  topics: string[];
}
