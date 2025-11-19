package ar.edu.utn.frc.tup.piii.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private Integer ataque;
    private Integer defensa;
    private Integer velocidad;

    @JsonProperty("imagen_url")
    private String imagenUrl;
}
