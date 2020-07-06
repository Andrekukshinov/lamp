import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Room} from "./models/Room";
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {log} from "util";
import {Country} from "./models/Country";

@Injectable({
  providedIn: 'root'
})
export class ApiService {


  constructor(private http: HttpClient, private router: Router) {
  }

  getAllRooms(): Observable<Room[]> {
    return this.http.get<Room[]>("/api");
  }



  getAllCountries (): Observable<Country[]> {
    return this.http.get<Country[]>("/api/countries");
  }

  getCurrentRoom(roomId: bigint): Observable<Room> {
    if (roomId == null) {
      return this.http.get<Room>("/api" + this.router.url);
    } else {
      return this.http.get<Room>("/api/room/" + roomId);
    }
  }
  createNewRoom(room: Room): Observable<Room> {

    console.log(room);
    return this.http.post<Room>("/api/create", room);
  }

  changeLampStatus(room: Room): Observable<number> {
    // let ley = {roomId: room.lamp.id, status: room.lamp.state}
    return this.http.post<number>("/api/room/lamp"/* + room.id*/, room);
  }
}
