package ar.edu.utn.frc.tup.piii.controllers;

import ar.edu.utn.frc.tup.piii.dtos.MatchDto;
import ar.edu.utn.frc.tup.piii.dtos.PlayMatchRequestDto;
import ar.edu.utn.frc.tup.piii.models.Match;
import ar.edu.utn.frc.tup.piii.services.MatchService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    private final ModelMapper modelMapper;

    @PostMapping("/play")
    public MatchDto playMatch(@RequestBody PlayMatchRequestDto playMatchRequestDto) {
        Match match = matchService.playMatch(playMatchRequestDto);
        return modelMapper.map(match, MatchDto.class);
    }
}
