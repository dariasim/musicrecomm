import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {
  private apiUrl = environment.backendBaseUrl + '/playlist/';

  constructor(private http: HttpClient) {}

  getPlaylist(artistName: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}${artistName}`);
  }
}
