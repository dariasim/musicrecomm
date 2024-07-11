import { Component } from '@angular/core';
import { PlaylistComponent } from './playlist/playlist.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less'],
  standalone: true,
  imports: [PlaylistComponent]  // Import PlaylistComponent here
})
export class AppComponent {}
