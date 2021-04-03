package dev.planet.star.wars.planet.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import dev.planet.star.wars.exception.ResourceNotFoundException;
import dev.planet.star.wars.planet.model.Planet;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PlanetControllerTest {

  @Autowired
  private PlanetController planetController;

  @Test
  public void dependencyInjectionTest() {
    assertThat(this.planetController).isNotNull();
  }

  @Test()
  public void shouldThrowExceptionWithRightMessage() {
    try {
      this.planetController.findById(String.valueOf(Long.MAX_VALUE));
      fail();
    } catch (ResourceNotFoundException e) {
      Long id = Long.MAX_VALUE;
      String message = e.getMessage().replace(".", "");
      assertThat(message).isEqualTo(String.format(LocaleContextHolder.getLocale(),
          "Information not found for identifier: %8s", String.valueOf(id)));
    }
  }

  @Test(expected = ResourceNotFoundException.class)
  public void findByIdThrowResourceNotFoudExcetion() {
    this.planetController.findById(String.valueOf(Long.MAX_VALUE));
  }

  @Test(expected = ResourceNotFoundException.class)
  public void findByNameThrowResourceNotFoudExcetion() {
    this.planetController.findByName(String.valueOf(Long.MAX_VALUE));
  }

  @Test
  public void deleteTest() {
    this.planetController.deleteById(String.valueOf(Long.MAX_VALUE));
  }

  @Test
  public void findAllTest() {
    final ResponseEntity<?> responseEntity = this.planetController.findAll();
    Assert.assertNotNull(responseEntity);
  }

  @Test
  public void createTest() throws IOException {
    Planet planet = new Planet();
    planet.setName("Alderaan");
    planet.setClimate("temperate");
    planet.setTerrain("grasslands, mountains");
    final ResponseEntity<?> responseEntity = this.planetController.create(planet);
    Assert.assertNotNull(responseEntity);
  }
}