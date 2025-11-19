package ar.edu.utn.frc.tup.piii.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayMatchRequestDto {
    private CharacterDto playerCharacter;
    private CharacterDto opponentCharacter;
    private DiceRollDto[] playerDices;
    private DiceRollDto[] opponentDices;
}
