package dev.planet.star.wars.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Classe responsável por manipular (encriptar e validar) a senha de um usuário e para acesso ao
 * swapi.dev.
 *
 * @version 1.0
 * @since 29.03.2021
 */
public class Utils {

  /**
   * Método responsável por gerar um hash utilizando BCrypt
   *
   * @return { @link String }
   */
  public static String gerarBCrypt(String password) {
    if (password == null) {
      return password;
    } else {
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      return bCryptPasswordEncoder.encode(password);
    }
  }

  /**
   * Método responsável por verificar se a senha é válida
   *
   * @return boolean
   */
  public static boolean isSenhaValida(String password, String encodedPassword) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder.matches(password, encodedPassword);
  }

  public static <T> List<T> jsonArrayToList(String json, Class<T> elementClass) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    CollectionType listType =
        objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
    return objectMapper.readValue(json, listType);
  }

  public static JsonObject getBuilder(String path, String searchquery) throws Exception {
    HttpGet httpGet;
    if (searchquery == null) {
      httpGet = new HttpGet("https://swapi.dev/api/" + path + "/");
    } else {
      httpGet = new HttpGet("https://swapi.dev/api/" + path + "/?search=" + searchquery);
    }
    return getRequest(httpGet);
  }

  public static JsonObject getRequest(HttpGet getRequest) throws IOException {
    HttpClient httpClient = HttpClientBuilder.create().build();
    getRequest.addHeader("accept", "application/json");
    HttpResponse response = httpClient.execute(getRequest);

    if (response.getStatusLine().getStatusCode() != 200) {
      throw new RuntimeException("Failed : HTTP error code : "
          + response.getStatusLine().getStatusCode());
    }

    BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader((response.getEntity().getContent())));
    String line;
    StringBuilder stringBuilder = new StringBuilder();
    while ((line = bufferedReader.readLine()) != null) {
      stringBuilder.append(line);
    }

    JsonObject jsonObject = deserialize(stringBuilder.toString());
    bufferedReader.close();
    return jsonObject;
  }

  public static JsonObject deserialize(String json) {
    Gson gson = new Gson();
    JsonObject jsonClass = gson.fromJson(json, JsonObject.class);
    return jsonClass;
  }

}
