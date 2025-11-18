package ar.edu.utn.frc.tup.piii.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {
    private Long id;
    private Long playerCharacterId;
    private Long opponentCharacterId;
    private Integer playerScore;
    private Integer opponentScore;
    private Long winnerCharacterId;
}
