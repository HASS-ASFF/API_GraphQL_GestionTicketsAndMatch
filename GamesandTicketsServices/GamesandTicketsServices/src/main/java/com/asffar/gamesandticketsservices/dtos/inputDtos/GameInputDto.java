package com.asffar.gamesandticketsservices.dtos.inputDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameInputDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dateAndTime;
    private String stadium;
    private String team1;
    private String team2;
    private Integer availableTickets;
}
