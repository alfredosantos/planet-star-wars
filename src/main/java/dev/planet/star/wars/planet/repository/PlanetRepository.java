package dev.planet.star.wars.planet.repository;

import dev.planet.star.wars.planet.model.Planet;
import java.util.Optional;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe que representa interface PlanetRepository
 *
 * @author Alfredo
 * @version 1.0
 * @since 29.03.2021
 */
@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {

  @DeleteQuery
  void deleteById(String id);

  Optional<Planet> findByName(String name);
}