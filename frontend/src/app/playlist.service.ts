import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {
  private apiUrl = 'http://localhost:5173/playlist/'; // Adjust the URL based on your backend API

  constructor(private http: HttpClient) {}

  getPlaylist(artistName: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}${artistName}`);
  }
}
