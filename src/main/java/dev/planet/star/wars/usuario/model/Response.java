package dev.planet.star.wars.usuario.model;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe responsável por encapsular o retorno de todas as requisições da API Planet Star Wars.
 *
 * @version 2.0
 * @since 29.03.2021
 */
@NoArgsConstructor
public class Response<T> {

  @Setter
  @Getter
  private T data;
  @Setter
  private Map<String, String> erros;

  public Map<String, String> getErros() {
    if (this.erros == null) {
      this.erros = new HashMap<String, String>();
    }
    return erros;
  }
}
