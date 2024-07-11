import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PlaylistService } from '../playlist.service';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.less'],
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  providers: [PlaylistService]
})
export class PlaylistComponent {
  artistName: string = '';
  playlistName: string = '';
  playlistDescription: string = '';
  songs: any[] = [];
  showResults: boolean = false;

  constructor(private playlistService: PlaylistService) {}

  generatePlaylist() {
    this.playlistService.getPlaylist(this.artistName).subscribe(response => {
      this.playlistName = response.playlistName;
      this.playlistDescription = response.playlistDescription;
      this.songs = response.data;
      this.showResults = true;
    });
  }
}
