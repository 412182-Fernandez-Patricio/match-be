package ar.edu.utn.frc.tup.piii.services.clients.impl;

import ar.edu.utn.frc.tup.piii.dtos.external.CharacterDto;
import ar.edu.utn.frc.tup.piii.services.clients.CharactersClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CharactersClientImpl implements CharactersClient {

    @Value("${characters.service.url}")
    private String charactersApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CharacterDto getCharacterById(Long id) {
        String url = charactersApiUrl + "/" + id;
        return restTemplate.getForObject(url, CharacterDto.class);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        String url = charactersApiUrl + "/random";
        return restTemplate.getForObject(url, CharacterDto.class);
    }

    @Override
    public List<CharacterDto> getCharacters() {
        CharacterDto[] response = restTemplate.getForObject(charactersApiUrl, CharacterDto[].class);
        return Arrays.asList(response);
    }
}
