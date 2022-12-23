package com.asffar.gamesandticketsservices.entities;

import com.asffar.gamesandticketsservices.enums.TicketState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Ticket {
    @Id
    private String id;
    private Double price;
    @Enumerated(EnumType.STRING)
    private TicketState state;
    private String ref;
    @ManyToOne
    private Game game;
}
