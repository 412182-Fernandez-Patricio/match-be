package ar.edu.utn.frc.tup.piii.services.impl;

import ar.edu.utn.frc.tup.piii.dtos.CharacterDto;
import ar.edu.utn.frc.tup.piii.dtos.DiceRollDto;
import ar.edu.utn.frc.tup.piii.dtos.PlayMatchRequestDto;
import ar.edu.utn.frc.tup.piii.entities.MatchEntity;
import ar.edu.utn.frc.tup.piii.models.Match;
import ar.edu.utn.frc.tup.piii.repositories.MatchRepository;
import ar.edu.utn.frc.tup.piii.services.MatchService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final ModelMapper modelMapper;

    public MatchServiceImpl(MatchRepository matchRepository, ModelMapper modelMapper) {
        this.matchRepository = matchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Match playMatch(PlayMatchRequestDto playMatchRequestDto) {
        CharacterDto playerCharacter = playMatchRequestDto.getPlayerCharacter();
        CharacterDto opponentCharacter = playMatchRequestDto.getOpponentCharacter();
        DiceRollDto[] playerDices = playMatchRequestDto.getPlayerDices();
        DiceRollDto[] opponentDices = playMatchRequestDto.getOpponentDices();

        // 4. Calculate Scores
        int playerTotalAttributes = playerCharacter.getAtaque() + playerCharacter.getDefensa() + playerCharacter.getVelocidad();
        int playerTotalDices = Stream.of(playerDices).mapToInt(DiceRollDto::getNumero).sum();
        int playerScore = playerTotalAttributes * playerTotalDices;

        int opponentTotalAttributes = opponentCharacter.getAtaque() + opponentCharacter.getDefensa() + opponentCharacter.getVelocidad();
        int opponentTotalDices = Stream.of(opponentDices).mapToInt(DiceRollDto::getNumero).sum();
        int opponentScore = opponentTotalAttributes * opponentTotalDices;

        // 5. Determine Winner
        Long winnerId;
        if (playerScore > opponentScore) {
            winnerId = playerCharacter.getId();
        } else {
            winnerId = opponentCharacter.getId();
        }

        // 6. Create Match model
        Match match = new Match();
        match.setPlayerCharacterId(playerCharacter.getId());
        match.setOpponentCharacterId(opponentCharacter.getId());
        match.setPlayerDiceRoll1(playerDices[0].getNumero());
        match.setPlayerDiceRoll2(playerDices[1].getNumero());
        match.setPlayerDiceRoll3(playerDices[2].getNumero());
        match.setOpponentDiceRoll1(opponentDices[0].getNumero());
        match.setOpponentDiceRoll2(opponentDices[1].getNumero());
        match.setOpponentDiceRoll3(opponentDices[2].getNumero());
        match.setPlayerScore(playerScore);
        match.setOpponentScore(opponentScore);
        match.setWinnerCharacterId(winnerId);

        // 7. Save match into DB
        MatchEntity matchEntity = modelMapper.map(match, MatchEntity.class);
        matchRepository.save(matchEntity);
        match.setId(matchEntity.getId());

        return match;
    }
}
