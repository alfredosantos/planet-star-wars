package dev.planet.star.wars.usuario.controller;

import dev.planet.star.wars.config.Message;
import dev.planet.star.wars.exception.ResourceNotFoundException;
import dev.planet.star.wars.usuario.mapper.UsuarioMapper;
import dev.planet.star.wars.usuario.model.Response;
import dev.planet.star.wars.usuario.model.Usuario;
import dev.planet.star.wars.usuario.model.UsuarioDTO;
import dev.planet.star.wars.usuario.service.UsuarioService;
import java.net.URI;
import java.util.Arrays;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Classe responsável por controllar(receber e enviar) requisições relacionadas a um {@link Usuario}
 * na API Planet Star Wars.
 *
 * @version 1.0
 * @since 29.03.2021
 */
@RestController
@RequestMapping("/api/api/planet-star-wars/v1/usuario")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;

  @Autowired
  private UsuarioMapper usuarioMapper;

  @Autowired
  private Message message;

  @PostMapping
  public ResponseEntity<Response<UsuarioDTO>> salvar(@Valid @RequestBody UsuarioDTO usuarioDTO,
      BindingResult bindingResult) {
    Response<UsuarioDTO> response = new Response<UsuarioDTO>();
    if (bindingResult.hasErrors()) {
      bindingResult.getAllErrors().forEach(error -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        response.getErros().put(fieldName, errorMessage);
      });
      return ResponseEntity.badRequest().body(response);
    }

    Usuario usuarioSaved = this.usuarioService
        .save(this.usuarioMapper.usuarioDTOtoUsuario(usuarioDTO));

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(usuarioSaved.getId())
        .toUri();

    response.setData(this.usuarioMapper.usuarioToUsuarioDTO(usuarioSaved));

    return ResponseEntity.created(location).body(response);

  }

  @GetMapping(path = "/{id}")
  @ResponseBody
  public ResponseEntity<?> findById(@PathVariable String id) {
    Optional<Usuario> optionalLog = this.usuarioService.findById(id);
    Usuario usuario = optionalLog
        .orElseThrow(() -> new ResourceNotFoundException(
            message.build("resource.not.found.for.identifier", Arrays.asList(id))));
    return new ResponseEntity<>(usuario, HttpStatus.OK);
  }
}
