package dev.planet.star.wars.usuario.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Classe que representa um usuário na API Planet Star Wars
 *
 * @author Alfredo
 * @version 1.0
 * @since 29.03.2021
 */
@Document(collection = "usuario")
@Data
@NoArgsConstructor
public class Usuario implements Serializable, UserDetails {

  @Id
  String id;

  @Indexed(unique = true)
  private String email;

  private String senha;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataCriacao;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataAtualizacao;

  public Usuario(String id, String email, String senha, Date dataCriacao,
      Date dataAtualizacao) {
    this.id = id;
    this.email = email;
    this.senha = senha;
    this.dataCriacao = dataCriacao;
    this.dataAtualizacao = dataAtualizacao;
  }

  @ApiModelProperty(hidden = true)
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @ApiModelProperty(hidden = true)
  @Override
  public String getPassword() {
    return this.senha;
  }

  @ApiModelProperty(hidden = true)
  @Override
  public String getUsername() {
    return this.email;
  }

  @ApiModelProperty(hidden = true)
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @ApiModelProperty(hidden = true)
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @ApiModelProperty(hidden = true)
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @ApiModelProperty(hidden = true)
  @Override
  public boolean isEnabled() {
    return true;
  }

  @PrePersist
  public void prePersiste() {
    this.dataCriacao = new Date();
  }

  @PreUpdate
  public void preUpdate() {
    this.dataAtualizacao = new Date();
  }

}