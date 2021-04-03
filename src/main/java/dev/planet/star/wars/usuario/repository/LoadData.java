package dev.planet.star.wars.usuario.repository;

import dev.planet.star.wars.usuario.model.Usuario;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0 Class responsible for entering the admin user during server startup
 * @since 29.03.2021
 */
@Configuration
@Slf4j
public class LoadData {

  @Bean
  public CommandLineRunner insertAdministratorInDatabase(UsuarioRepository usuarioRepository) {
    return args -> {
      Usuario usuario = usuarioRepository.findByEmail("adm@planetstarwars.com.br");
      if (usuario == null || usuario.getId().isEmpty()) {
        usuarioRepository
            .save(new Usuario(null, "adm@planetstarwars.com.br", "12345678", new Date(),
                null));
      } else {
        usuarioRepository
            .save(new Usuario(usuario.getId(), "adm@planetstarwars.com.br", "12345678", new Date(),
                new Date()));
      }
      log.info("Administrator successfully inserted.");
    };
  }
}