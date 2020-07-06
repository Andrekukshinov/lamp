import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RoomComponent } from './room/room.component';
import { RoomCreationComponent } from './room-creation/room-creation.component';
import { CountriesRoomsComponent } from './countries-rooms/countries-rooms.component';
import {HttpClientModule} from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";



const appRoutes: Routes = [
  {path: '', component: CountriesRoomsComponent},
  {path: 'room/create', pathMatch: 'full', component: RoomCreationComponent},
  {path: 'room/:id', component: RoomComponent}

];
@NgModule({
  declarations: [
    AppComponent,
    RoomComponent,
    RoomCreationComponent,
    CountriesRoomsComponent
  ],
    imports: [
        [HttpClientModule],
        BrowserModule,
        [RouterModule.forRoot(appRoutes)],
        AppRoutingModule,
        BrowserAnimationsModule,
        MatOptionModule,
        MatSelectModule,
        MatInputModule,
        ReactiveFormsModule,
        FormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
