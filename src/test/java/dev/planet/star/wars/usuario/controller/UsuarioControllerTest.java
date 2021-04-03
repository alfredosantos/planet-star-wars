package dev.planet.star.wars.usuario.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import dev.planet.star.wars.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioControllerTest {

  @Autowired
  private UsuarioController usuarioController;

  @Test
  public void dependencyInjectionTest() {
    assertThat(this.usuarioController).isNotNull();
  }

  @Test(expected = ResourceNotFoundException.class)
  public void throwResourceNotFoudExcetion() {
    this.usuarioController.findById(String.valueOf(Long.MAX_VALUE));
  }

  @Test()
  public void shouldThrowExceptionWithRightMessage() {
    try {
      this.usuarioController.findById(String.valueOf(Long.MAX_VALUE));
      fail();
    } catch (ResourceNotFoundException e) {
      Long id = Long.MAX_VALUE;
      String message = e.getMessage().replace(".", "");
      assertThat(message).isEqualTo(String.format(LocaleContextHolder.getLocale(),
          "Information not found for identifier: %d", id));
    }
  }

}