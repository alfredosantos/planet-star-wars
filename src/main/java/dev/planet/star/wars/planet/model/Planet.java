package dev.planet.star.wars.planet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.List;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planet")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Planet {

  @Id
  String id;

  @Indexed(unique = true)
  String name;
  String climate;
  String terrain;
  @JsonProperty(access = Access.WRITE_ONLY)
  List<String> films;
  int amountFilms;
}
