package dev.planet.star.wars.login.controller;

import dev.planet.star.wars.usuario.mapper.UsuarioMapper;
import dev.planet.star.wars.usuario.model.Response;
import dev.planet.star.wars.usuario.model.Usuario;
import dev.planet.star.wars.usuario.model.UsuarioDTO;
import dev.planet.star.wars.usuario.service.UsuarioService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/planet-star-wars/v1/login")
public class LoginController {

  @Autowired
  private UsuarioService usuarioService;

  @Autowired
  private UsuarioMapper usuarioMapper;

  @PostMapping
  public ResponseEntity<Response<UsuarioDTO>> logar(@Valid @RequestBody UsuarioDTO usuarioDTO,
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
    Usuario usuario = this.usuarioService.logar(this.usuarioMapper.usuarioDTOtoUsuario(usuarioDTO));
    response.setData(this.usuarioMapper.usuarioToUsuarioDTO(usuario));
    return ResponseEntity.ok(response);
  }
}
