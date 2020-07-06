import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Room} from "../models/Room";
import {ApiService} from "../api.service";
import {CurrentRoomService} from "../services/current-room.service";
import {Router} from "@angular/router";
import {Lamp} from "../models/Lamp";
import {WebSocketAPI} from "../services/webSocketApi";

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit, OnDestroy {

  room: Room;

  webSocketAPI: WebSocketAPI;


  constructor(private apiService: ApiService, private roomService: CurrentRoomService,  private router: Router) {
     this.apiService.getCurrentRoom(roomService.getRoomId()).subscribe(room => {
       this.room = room;
       this.webSocketAPI = new WebSocketAPI(this, this.room);
       this.connect();
     });
    setTimeout(() => {
      if (this.room == null) {
        this.router.navigate(['/']);
      }
    },1000);

  }

  ngOnInit() {
  }

  switchTheLight():void{
    this.room.lamp.state = !this.room.lamp.state;
    this.webSocketAPI._send(JSON.stringify(this.room));
  }
  handleMessage(lamp):void {
      this.room.lamp = lamp;
  }
  connect(){
    this.webSocketAPI._connect();
  }

  disconnect(){
    this.webSocketAPI._disconnect();
  }

  sendMessage(){
    this.webSocketAPI._send(this.room);
  }

  ngOnDestroy(): void {
    this.disconnect();
  }
}
