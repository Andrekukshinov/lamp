import { Component, OnInit } from '@angular/core';
import {ApiService} from "../api.service";
import {Room} from "../models/Room";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Country} from "../models/Country";
import {Lamp} from "../models/Lamp";
import {log} from "util";
import {Router} from "@angular/router";

@Component({
  selector: 'app-room-creation',
  templateUrl: './room-creation.component.html',
  styleUrls: ['./room-creation.component.css']
})
export class RoomCreationComponent implements OnInit {

  countries: Country[];
  selectedValue: Country = null;

  private createdRoom: Room;

  roomForm: FormGroup;

  constructor(private router: Router, private apiService:ApiService, private formBuilder: FormBuilder) {
      this.apiService.getAllCountries().subscribe(countries => this.countries = countries);
  }

  ngOnInit() {
    this.roomForm = this.formBuilder.group({
      roomName:['', [Validators.required, Validators.minLength(4), Validators.maxLength(30)]],
    });
    this.roomForm.valueChanges.subscribe();

  }

  public createRoom():Room {
    this.createdRoom = new Room();
    this.createdRoom.roomName = this.roomForm.get('roomName').value;
    this.createdRoom.country = this.selectedValue;
    this.createdRoom.lamp = new Lamp();
    this.apiService.createNewRoom(this.createdRoom).subscribe(room =>
      setTimeout(() => {
      if (room != null) {
        this.router.navigate(['/room/'+ room.id]);
      }
    },1000));
    return this.createdRoom;

  }

}
