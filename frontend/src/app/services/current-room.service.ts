import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CurrentRoomService {

  roomId: bigint;

  constructor() {
  }

  setRoomId(roomId: bigint): void {
    this.roomId = roomId;
  }
  getRoomId(): bigint {
    return this.roomId;
  }


}
