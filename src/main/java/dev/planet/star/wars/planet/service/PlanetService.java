package dev.planet.star.wars.planet.service;

/**
 * Classe que representa um log na API Planet Star Wars
 *
 * @author Alfredo
 * @version 1.0
 * @since 29.03.2021
 */

import dev.planet.star.wars.planet.model.Planet;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface PlanetService {

  public List<Planet> findAll();

  public Optional<Planet> findById(String id);

  public Planet save(Planet planet);

  public Optional<Planet> findByName(String name);

  void deleteById(String id);
}