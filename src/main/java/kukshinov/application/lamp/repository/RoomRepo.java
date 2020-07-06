package kukshinov.application.lamp.repository;

import kukshinov.application.lamp.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends CrudRepository<Room, Long> {

}
