package com.asffar.gamesandticketsservices.exceptions.custome;

public class InvalidTicketsNumberException extends Exception{
    public InvalidTicketsNumberException(Integer num){
        super("Number of tickets should be between 1 and 2022, you give it a value of:" + num);
    }
}
