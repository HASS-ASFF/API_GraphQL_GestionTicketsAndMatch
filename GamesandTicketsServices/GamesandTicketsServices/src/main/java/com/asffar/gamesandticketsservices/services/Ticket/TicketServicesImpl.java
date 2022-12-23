package com.asffar.gamesandticketsservices.services.Ticket;

import com.asffar.gamesandticketsservices.dtos.inputDtos.TicketInputDto;
import com.asffar.gamesandticketsservices.dtos.outputDtos.TicketOutputDto;
import com.asffar.gamesandticketsservices.entities.Game;
import com.asffar.gamesandticketsservices.entities.Ticket;
import com.asffar.gamesandticketsservices.enums.TicketState;
import com.asffar.gamesandticketsservices.exceptions.custome.*;
import com.asffar.gamesandticketsservices.mappers.MapperService;
import com.asffar.gamesandticketsservices.repositories.GameRepository;
import com.asffar.gamesandticketsservices.repositories.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class TicketServicesImpl implements TicketServices {
    private TicketRepository ticketRepository;
    private MapperService mapperService;
    private GameRepository gameRepository;

    @Override
    public TicketOutputDto buyTicket(TicketInputDto ticketInputDto) throws MissingFieldsException, GameIdNotFoundException, TicketsSoldOutException {
        if (ticketInputDto.getGameId() == null || ticketInputDto.getGameId().isEmpty()
        || ticketInputDto.getPrice().isNaN() || ticketInputDto.getPrice() <= 0)
            throw new MissingFieldsException();
        Game game = gameRepository.findById(ticketInputDto.getGameId())
                .orElseThrow(
                        () -> new GameIdNotFoundException(ticketInputDto.getGameId())
                );
        if (game.getAvailableTicketNumber() == 0)
            throw new TicketsSoldOutException();
        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID().toString());
        ticket.setPrice(ticketInputDto.getPrice());
        ticket.setState(TicketState.ENABLED);
        ticket.setRef(UUID.randomUUID().toString().substring(0,16));
        ticket.setGame(game);

        game.setAvailableTicketNumber(game.getAvailableTicketNumber() - 1);
        gameRepository.save(game);
        return mapperService.fromTicket(ticketRepository.save(ticket));
    }

    @Override
    public void validateTicket(String id) throws TicketIdNotFoundException, TicketAlreadyValidatedException {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketIdNotFoundException(id));
        if (ticket.getState() == TicketState.DISABLED)
            throw new TicketAlreadyValidatedException(id);
        //Do not validate ticket until the day of the game
        ticket.setState(TicketState.DISABLED);
        ticketRepository.save(ticket);
    }
}
