import { Routes } from '@angular/router';
import { AuthorListComponent } from './components/author-list/author-list';

export const routes: Routes = [
  { path: '', component: AuthorListComponent },
  { path: 'authors', component: AuthorListComponent }
];
