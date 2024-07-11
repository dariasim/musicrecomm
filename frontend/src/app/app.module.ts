// src/app/app.module.ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';  // Import FormsModule
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { PlaylistComponent } from './playlist/playlist.component';

@NgModule({

  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppComponent,
    PlaylistComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
