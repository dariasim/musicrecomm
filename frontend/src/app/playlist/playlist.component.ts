import { Component } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PlaylistService } from '../playlist.service';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.less'],
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, NgOptimizedImage],
  providers: [PlaylistService]
})
export class PlaylistComponent {
  artistName: string = '';
  playlistName: string = '';
  playlistDescription: string = '';
  songs: any[] = [];
  showResults: boolean = false;
  isLoading: boolean = false; // Add this line

  constructor(private playlistService: PlaylistService) {}

  generatePlaylist() {
    this.isLoading = true; // Set loading to true when the request starts
    this.playlistService.getPlaylist(this.artistName).subscribe(response => {
      this.playlistName = response.playlistName;
      this.playlistDescription = response.playlistDescription;
      this.songs = response.songs;
      this.showResults = true;
      this.isLoading = false; // Set loading to false when the request finishes
    }, error => {
      console.error('Error fetching playlist', error);
      this.isLoading = false; // Set loading to false in case of an error
    });
  }
}
