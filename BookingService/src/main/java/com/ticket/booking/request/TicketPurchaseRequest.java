package com.ticket.booking.request;


import com.ticket.booking.model.Train;
import com.ticket.booking.model.User;

public class TicketPurchaseRequest {

    User user;
    String sourceStation;
    String endStation;
    Double ticketFare;
    Integer trainNumber;

    //Date

    //SeatPreference

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSourceStation() {
        return sourceStation;
    }

    public void setSourceStation(String sourceStation) {
        this.sourceStation = sourceStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public Double getTicketFare() {
        return ticketFare;
    }

    public void setTicketFare(Double ticketFare) {
        this.ticketFare = ticketFare;
    }

    public Integer getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Integer trainNumber) {
        this.trainNumber = trainNumber;
    }
}
