package com.asffar.gamesandticketsservices.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Game {
    @Id
    private String id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dateAndTime;
    private String stadium;
    private String team1;
    private String team2;
    private String ref;
    private Integer availableTicketNumber;
    @OneToMany(mappedBy = "game")
    @Column(length = 2022)
    private List<Ticket> tickets;
}
