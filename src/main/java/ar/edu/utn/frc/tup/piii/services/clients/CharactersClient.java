package ar.edu.utn.frc.tup.piii.services.clients;

import ar.edu.utn.frc.tup.piii.dtos.external.CharacterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharactersClient {

    CharacterDto getCharacterById(Long id);
    CharacterDto getRandomCharacter();
    List<CharacterDto> getCharacters();

}
