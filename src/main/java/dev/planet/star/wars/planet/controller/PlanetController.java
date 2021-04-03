package dev.planet.star.wars.planet.controller;

import static dev.planet.star.wars.util.Utils.getBuilder;
import static dev.planet.star.wars.util.Utils.jsonArrayToList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.planet.star.wars.config.Message;
import dev.planet.star.wars.exception.ResourceNotFoundException;
import dev.planet.star.wars.planet.model.Planet;
import dev.planet.star.wars.planet.service.PlanetService;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe que representa um log na API Planet Star Wars
 *
 * @author Alfredo
 * @version 1.0
 * @since 25.03.2021
 */
@RestController
@AllArgsConstructor
@RequestMapping("/planets")
public class PlanetController {

  @Autowired
  private PlanetService planetService;

  @Autowired
  private Message message;

  @GetMapping(path = "/id/{id}")
  @ResponseBody
  public ResponseEntity<?> findById(@PathVariable String id) {
    Optional<Planet> optionalLog = this.planetService.findById(id);
    Planet planet = optionalLog.orElseThrow(() -> new ResourceNotFoundException(
        message.build("resource.not.found.for.identifier", Arrays.asList(id))));
    return new ResponseEntity<>(planet, HttpStatus.OK);
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<?> create(@RequestBody Planet planet) throws IOException {
    try {
      final JsonObject planets = getBuilder("planets", planet.getName());
      final JsonParser parser = new JsonParser();
      final JsonElement jsonElement = parser.parse(String.valueOf(planets));
      final JsonObject rootObject = jsonElement.getAsJsonObject();
      final JsonArray childObject = rootObject.getAsJsonArray("results");
      final List<Planet> planetList = jsonArrayToList(childObject.toString(), Planet.class);
      planet.setFilms(planetList.get(0).getFilms());
    } catch (Exception e) {
      e.printStackTrace();
    }
    final Optional<Planet> planetWork = planetService.findByName(planet.getName());
    if (!planetWork.isPresent()) {
      return new ResponseEntity<>(this.planetService.save(planet), HttpStatus.ACCEPTED);
    } else {
      planet.setId(planetWork.get().getId());
      return new ResponseEntity<>(this.planetService.save(planet), HttpStatus.ACCEPTED);
    }
  }

  @GetMapping
  @ResponseBody
  public ResponseEntity<?> findAll() {
    return new ResponseEntity<>(
        this.planetService.findAll(),
        HttpStatus.OK);
  }

  @GetMapping(path = "/name/{name}")
  @ResponseBody
  public ResponseEntity<?> findByName(@PathVariable String name) {
    Optional<Planet> optionalLog = this.planetService.findByName(name);
    Planet planet = optionalLog.orElseThrow(() -> new ResourceNotFoundException(
        message.build("resource.not.found.for.identifier", Arrays.asList(name))));
    return new ResponseEntity<>(planet, HttpStatus.OK);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseBody
  public void deleteById(@PathVariable String id) {
    this.planetService.deleteById(id);
  }
}