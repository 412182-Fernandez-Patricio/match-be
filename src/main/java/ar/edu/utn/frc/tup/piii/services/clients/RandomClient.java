package ar.edu.utn.frc.tup.piii.services.clients;

import ar.edu.utn.frc.tup.piii.dtos.external.DiceRollDto;
import org.springframework.stereotype.Service;

@Service
public interface RandomClient {

    DiceRollDto getDiceRoll();
}
