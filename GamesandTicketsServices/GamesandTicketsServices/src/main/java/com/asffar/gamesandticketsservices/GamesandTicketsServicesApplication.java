package com.asffar.gamesandticketsservices;

import com.asffar.gamesandticketsservices.dtos.inputDtos.GameInputDto;
import com.asffar.gamesandticketsservices.dtos.inputDtos.TicketInputDto;
import com.asffar.gamesandticketsservices.dtos.outputDtos.GameOutputDto;
import com.asffar.gamesandticketsservices.exceptions.custome.GameIdNotFoundException;
import com.asffar.gamesandticketsservices.exceptions.custome.InvalidTicketsNumberException;
import com.asffar.gamesandticketsservices.exceptions.custome.MissingFieldsException;
import com.asffar.gamesandticketsservices.exceptions.custome.TicketsSoldOutException;
import com.asffar.gamesandticketsservices.services.Game.GameServices;
import com.asffar.gamesandticketsservices.services.Ticket.TicketServices;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
@AllArgsConstructor
public class GamesandTicketsServicesApplication {

    GameServices gameServices;
    TicketServices ticketServices;

    public static void main(String[] args) {
        SpringApplication.run(GamesandTicketsServicesApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner (){
        return args -> {
            String d1 = "2022-11-18 12:00";
            String d2 = "2022-12-01 12:45";
            String d3 = "2022-11-13 20:15";
            String d4 = "2022-11-20 22:00";
            String d5 = "2022-11-15 18:30";
            String d6 = "2022-11-22 12:45";

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date1 = format.parse(d1);
            Date date2 = format.parse(d2);
            Date date3 = format.parse(d3);
            Date date4 = format.parse(d4);
            Date date5 = format.parse(d5);
            Date date6 = format.parse(d6);

            Stream.of(
                    new GameInputDto(date1, "Stadium1", "Team1", "Team2", 2022),
                    new GameInputDto(date2, "Stadium2", "Team3", "Team4", 1000),
                    new GameInputDto(date3, "Stadium3", "Team1", "Team4", 500),
                    new GameInputDto(date4, "Stadium1", "Team3", "Team2", 2000),
                    new GameInputDto(date5, "Stadium2", "Team4", "Team2", 700),
                    new GameInputDto(date6, "Stadium2", "Team1", "Team3", 1500)
            ).forEach(elem -> {
                GameOutputDto gameOutputDto = null;
                try {
                    gameOutputDto = gameServices.createGame(elem);
                } catch (MissingFieldsException | InvalidTicketsNumberException e) {
                    throw new RuntimeException(e);
                }
                Random random = new Random();
                int tickets = random.nextInt(0, elem.getAvailableTickets());
                for(int i=0; i< tickets; i++){
                    try {
                        ticketServices.buyTicket(
                                new TicketInputDto(random.nextDouble(1500.0),
                                        gameOutputDto.getId())
                        );
                    } catch (MissingFieldsException | GameIdNotFoundException | TicketsSoldOutException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        };
    }
}
