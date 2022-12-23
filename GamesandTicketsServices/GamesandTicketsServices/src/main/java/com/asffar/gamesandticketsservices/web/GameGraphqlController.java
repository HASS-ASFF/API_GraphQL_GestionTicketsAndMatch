package com.asffar.gamesandticketsservices.web;

import com.asffar.gamesandticketsservices.dtos.inputDtos.GameInputDto;
import com.asffar.gamesandticketsservices.dtos.outputDtos.GameOutputDto;
import com.asffar.gamesandticketsservices.exceptions.custome.InvalidTicketsNumberException;
import com.asffar.gamesandticketsservices.exceptions.custome.MissingFieldsException;
import com.asffar.gamesandticketsservices.services.Game.GameServices;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GameGraphqlController {
    private GameServices gameServices;

    @MutationMapping
    private GameOutputDto addGame(@Argument GameInputDto gameInputDto) throws MissingFieldsException, InvalidTicketsNumberException {
        return gameServices.createGame(gameInputDto);
    }

    @QueryMapping
    private List<GameOutputDto> getGames(){
        return gameServices.gamesList();
    }
}
