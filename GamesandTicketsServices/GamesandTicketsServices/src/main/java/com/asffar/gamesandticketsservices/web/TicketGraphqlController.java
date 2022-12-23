package com.asffar.gamesandticketsservices.web;

import com.asffar.gamesandticketsservices.dtos.inputDtos.TicketInputDto;
import com.asffar.gamesandticketsservices.dtos.outputDtos.TicketOutputDto;
import com.asffar.gamesandticketsservices.exceptions.custome.*;
import com.asffar.gamesandticketsservices.services.Ticket.TicketServices;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class TicketGraphqlController {
    private TicketServices ticketServices;

    @MutationMapping
    private TicketOutputDto getTicket(@Argument TicketInputDto ticketInputDto) throws GameIdNotFoundException, MissingFieldsException, TicketsSoldOutException {
        return ticketServices.buyTicket(ticketInputDto);
    }

    @MutationMapping
    private void updateTicket(@Argument String id) throws TicketIdNotFoundException, TicketAlreadyValidatedException {
        ticketServices.validateTicket(id);
    }
}
