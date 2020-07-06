import * as Stomp from "stompjs";
import * as SockJS from 'sockjs-client';
import {RoomComponent} from "../room/room.component";
import {Room} from "../models/Room";


export class WebSocketAPI {
  webSocketEndPoint: string = 'http://localhost:8080/ws';
  room: Room;
  messageBrokerAddress: string = "/room/";
  stompClient: any;
  roomComponent: RoomComponent;
  constructor(roomComponent: RoomComponent, room: Room){
    this.roomComponent = roomComponent;
    this.room = room;
  }
  _connect() {
    let ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    const _this = this;
    _this.stompClient.connect({},  (frame) => {
      _this.stompClient.subscribe(_this.messageBrokerAddress  + this.room.id, function (sdkEvent) {
        _this.onMessageReceived(sdkEvent);
      });
    }, this.errorCallBack);
  };

  _disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
  }

  // on error, schedule a reconnection attempt
  errorCallBack(error) {
    setTimeout(() => {
      this._connect();
    }, 5000);
  }

  /**
   * Send message to sever via web socket
   * @param room
   */
  _send(room) {
    console.log(this.room);
    this.stompClient.send("/mb/room/lamp/" + this.room.id, {},room);
  }

  onMessageReceived(message) {
    this.roomComponent.handleMessage(JSON.parse(message.body));
  }
}

