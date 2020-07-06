package kukshinov.application.lamp.repository;

import kukshinov.application.lamp.entity.Lamp;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LampRepo extends CrudRepository<Lamp, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Lamp l SET l.state = :changeState WHERE l.id = :id")
     int changeLampStatus(@Param("changeState") boolean status, @Param("id")long id);
}
