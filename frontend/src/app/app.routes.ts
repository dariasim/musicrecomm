// src/app/app.routes.ts
import { Routes } from '@angular/router';
import { PlaylistComponent } from './playlist/playlist.component';

export const routes: Routes = [
  { path: '', redirectTo: '/playlist', pathMatch: 'full' },
  { path: 'playlist', component: PlaylistComponent }
];
