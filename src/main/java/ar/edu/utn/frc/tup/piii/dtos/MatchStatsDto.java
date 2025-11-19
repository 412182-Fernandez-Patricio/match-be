package ar.edu.utn.frc.tup.piii.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchStatsDto {
    private Long characterId;
    private Integer gamesPlayed;
    private Integer wins;
    private Integer losses;
}
