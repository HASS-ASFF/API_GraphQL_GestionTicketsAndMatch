package com.asffar.gamesandticketsservices.repositories;

import com.asffar.gamesandticketsservices.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}
