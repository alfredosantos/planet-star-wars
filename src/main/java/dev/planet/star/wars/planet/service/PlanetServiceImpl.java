package dev.planet.star.wars.planet.service;

import dev.planet.star.wars.planet.model.Planet;
import dev.planet.star.wars.planet.repository.PlanetRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe que representa um log na API Planet Star Wars
 *
 * @author Alfredo
 * @version 1.0
 * @since 29.03.2021
 */
@Service
public class PlanetServiceImpl implements PlanetService {

  @Autowired
  private PlanetRepository repository;

  public List<Planet> findAll() {
    return this.repository.findAll();
  }

  @Override
  public Optional<Planet> findById(String id) {
    return this.repository.findById(id);
  }

  @Override
  public Planet save(Planet planet) {
    return this.repository.save(planet);
  }

  @Override
  public Optional<Planet> findByName(String name) {
    return this.repository.findByName(name);
  }

  @Override
  public void deleteById(String id) {
    this.repository.deleteById(id);
  }

}
