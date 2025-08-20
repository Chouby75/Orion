import { Topic } from './topic';

export interface Profile {
  id: number;
  username: string;
  email: string;
  topics: Topic[];
}

export interface ProfileUpdate {
  username?: string;
  email?: string;
  password?: string;
}
