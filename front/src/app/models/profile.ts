import { Topic } from './topic';

export interface Profile {
  id: number;
  username: string;
  email: string;
  subscribedTopics: Topic[];
}

export interface ProfileUpdate {
  username?: string;
  email?: string;
  password?: string;
}
