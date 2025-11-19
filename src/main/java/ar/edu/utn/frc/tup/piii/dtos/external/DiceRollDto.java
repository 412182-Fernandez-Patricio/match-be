package ar.edu.utn.frc.tup.piii.dtos.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiceRollDto {
    private Integer dice1;
    private Integer dice2;
    private Integer dice3;
}
