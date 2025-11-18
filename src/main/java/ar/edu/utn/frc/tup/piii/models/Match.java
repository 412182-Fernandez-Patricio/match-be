package ar.edu.utn.frc.tup.piii.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    private Long id;
    private Long playerCharacterId;
    private Long opponentCharacterId;
    private Integer playerDiceRoll1;
    private Integer playerDiceRoll2;
    private Integer playerDiceRoll3;
    private Integer opponentDiceRoll1;
    private Integer opponentDiceRoll2;
    private Integer opponentDiceRoll3;
    private Integer playerScore;
    private Integer opponentScore;
    private Long winnerCharacterId;
}
