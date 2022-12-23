package com.asffar.gamesandticketsservices.dtos.inputDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketInputDto {
    private Double price;
    private String gameId;
}
