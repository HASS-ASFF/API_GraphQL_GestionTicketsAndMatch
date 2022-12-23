package com.asffar.gamesandticketsservices.mappers;

import com.asffar.gamesandticketsservices.dtos.outputDtos.GameOutputDto;
import com.asffar.gamesandticketsservices.dtos.outputDtos.TicketOutputDto;
import com.asffar.gamesandticketsservices.entities.Game;
import com.asffar.gamesandticketsservices.entities.Ticket;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MapperService {
    public GameOutputDto fromGame(Game game){
        GameOutputDto gameOutputDto = new GameOutputDto();
        BeanUtils.copyProperties(game, gameOutputDto);
        return  gameOutputDto;
    }

    public TicketOutputDto fromTicket(Ticket ticket){
        TicketOutputDto ticketOutputDto = new TicketOutputDto();
        BeanUtils.copyProperties(ticket, ticketOutputDto);
        ticketOutputDto.setGameOutputDto(this.fromGame(ticket.getGame()));
        return ticketOutputDto;
    }
}
