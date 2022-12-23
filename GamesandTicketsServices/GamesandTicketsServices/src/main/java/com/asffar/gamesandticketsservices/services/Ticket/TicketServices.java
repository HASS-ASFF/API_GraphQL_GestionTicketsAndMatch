package com.asffar.gamesandticketsservices.services.Ticket;

import com.asffar.gamesandticketsservices.dtos.inputDtos.TicketInputDto;
import com.asffar.gamesandticketsservices.dtos.outputDtos.TicketOutputDto;
import com.asffar.gamesandticketsservices.exceptions.custome.*;

public interface TicketServices {
    TicketOutputDto buyTicket(TicketInputDto ticketInputDto) throws MissingFieldsException, GameIdNotFoundException, TicketsSoldOutException;

    void validateTicket(String id) throws TicketIdNotFoundException, TicketAlreadyValidatedException;
}
