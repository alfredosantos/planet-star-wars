package dev.planet.star.wars.usuario.mapper;

import dev.planet.star.wars.usuario.model.Usuario;
import dev.planet.star.wars.usuario.model.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Interface responsável por definir o mapeamento de {@link Usuario} para um {@link UsuarioDTO} e
 * vice-versa na API Planet Star Wars.
 *
 * @version 2.0
 * @since 29.03.2021
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

  @Mappings({
      @Mapping(target = "id", source = "usuario.id"),
      @Mapping(target = "email", source = "usuario.email"),
      @Mapping(target = "senha", source = "usuario.senha"),
  })
  UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

  @Mappings({
      @Mapping(target = "id", source = "usuarioDTO.id"),
      @Mapping(target = "email", source = "usuarioDTO.email"),
      @Mapping(target = "senha", source = "usuarioDTO.senha"),
  })
  Usuario usuarioDTOtoUsuario(UsuarioDTO usuarioDTO);
}
