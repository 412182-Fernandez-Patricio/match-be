package ar.edu.utn.frc.tup.piii.services.clients.impl;

import ar.edu.utn.frc.tup.piii.dtos.external.DiceRollDto;
import ar.edu.utn.frc.tup.piii.services.clients.RandomClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RandomClientImpl implements RandomClient {

    @Value("${random.service.url}")
    private String randomApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public DiceRollDto getDiceRoll() {
        return restTemplate.getForObject(randomApiUrl, DiceRollDto.class);
    }
}
