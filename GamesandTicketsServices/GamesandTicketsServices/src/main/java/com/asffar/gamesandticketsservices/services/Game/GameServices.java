package com.asffar.gamesandticketsservices.services.Game;

import com.asffar.gamesandticketsservices.dtos.inputDtos.GameInputDto;
import com.asffar.gamesandticketsservices.dtos.outputDtos.GameOutputDto;
import com.asffar.gamesandticketsservices.exceptions.custome.InvalidTicketsNumberException;
import com.asffar.gamesandticketsservices.exceptions.custome.MissingFieldsException;

import java.util.List;

public interface GameServices {
    GameOutputDto createGame(GameInputDto gameInputDto) throws MissingFieldsException, InvalidTicketsNumberException;
    List<GameOutputDto> gamesList();
}
