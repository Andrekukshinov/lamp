import { TestBed } from '@angular/core/testing';

import { CurrentRoomService } from './current-room.service';

describe('CurrentRoomService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CurrentRoomService = TestBed.get(CurrentRoomService);
    expect(service).toBeTruthy();
  });
});
