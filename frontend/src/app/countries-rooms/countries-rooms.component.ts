import { Component, OnInit } from '@angular/core';
import {Room} from "../models/Room";
import {ApiService} from "../api.service";
import {CurrentRoomService} from "../services/current-room.service";

@Component({
  selector: 'app-countries-rooms',
  templateUrl: './countries-rooms.component.html',
  styleUrls: ['./countries-rooms.component.css']
})
export class CountriesRoomsComponent implements OnInit {

  rooms: Room[];

  roomsViaLamp: Room[];


  constructor(private apiService: ApiService, private roomService: CurrentRoomService) {
    this.apiService.getAllRooms().subscribe(rooms =>
      this.rooms = rooms
    );

  }

  ngOnInit() {

  }


  getEnteredRoom(roomId: number):void{
       let tempId: bigint = roomId as unknown as bigint;
       this.roomService.setRoomId(tempId);
  }


}
