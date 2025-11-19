package ar.edu.utn.frc.tup.piii.services;

import ar.edu.utn.frc.tup.piii.dtos.PlayMatchRequestDto;
import ar.edu.utn.frc.tup.piii.models.Match;

public interface MatchService {
    Match playMatch(PlayMatchRequestDto playMatchRequestDto);
}
