package dev.planet.star.wars.usuario.repository;

import dev.planet.star.wars.usuario.model.Usuario;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, Long> {

  Usuario findByEmail(String email);

  Optional<Usuario> findById(String id);
}