import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CountriesRoomsComponent } from './countries-rooms.component';

describe('CountriesRoomsComponent', () => {
  let component: CountriesRoomsComponent;
  let fixture: ComponentFixture<CountriesRoomsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CountriesRoomsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CountriesRoomsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
