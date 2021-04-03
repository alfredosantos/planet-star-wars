package dev.planet.star.wars.usuario.service;

import dev.planet.star.wars.usuario.model.Usuario;
import dev.planet.star.wars.usuario.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Transactional
  @Override
  public Usuario save(Usuario usuario) {
    return this.usuarioRepository.save(usuario);
  }

  @Transactional(readOnly = true)
  @Override
  public Usuario findByEmail(String email) {
    return this.usuarioRepository.findByEmail(email);
  }

  @Override
  public Usuario logar(Usuario usuario) {
    return null;
  }

  @Override
  public Optional<Usuario> findById(String id) {
    return usuarioRepository.findById(id);
  }

}
