package kukshinov.application.lamp.repository;

import kukshinov.application.lamp.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepo extends CrudRepository<Country, Long> {
}
