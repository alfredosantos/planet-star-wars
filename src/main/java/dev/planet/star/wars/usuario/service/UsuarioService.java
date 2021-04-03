package dev.planet.star.wars.usuario.service;

import dev.planet.star.wars.usuario.model.Usuario;
import java.util.Optional;

/**
 * Interface que contém as assinaturas dos serviços relacionados á usuário.
 *
 * @version 3.0
 * @since 29.03.2021
 */
public interface UsuarioService {

  Usuario save(Usuario usuario);

  Usuario findByEmail(String email);

  Usuario logar(Usuario usuario);

  Optional<Usuario> findById(String id);
}
