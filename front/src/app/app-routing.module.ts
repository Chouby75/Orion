import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { MainComponent } from './pages/main/main.component';
import { FeedComponent } from './components/feed/feed.component';
import { PostFormComponent } from './components/post-form/post-form.component';
import { TopicComponent } from './components/topic/topic.component';
import { PostSpecComponent } from './components/post-spec/post-spec.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'main',
    component: MainComponent,
    children: [
      { path: 'feed', component: FeedComponent },
      { path: 'feed/new', component: PostFormComponent },
      { path: 'feed/:id', component: PostSpecComponent },
      { path: 'topics', component: TopicComponent },
      { path: 'profile', component: UserProfileComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
