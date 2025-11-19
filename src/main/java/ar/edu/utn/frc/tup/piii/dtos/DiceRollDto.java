package ar.edu.utn.frc.tup.piii.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiceRollDto {
    private Integer numero;

    @JsonProperty("imagen_url")
    private String imagenUrl;
}
