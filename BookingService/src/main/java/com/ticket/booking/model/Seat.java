package com.ticket.booking.model;

public class Seat {

    private Integer seatNumber;

    private Enum<SeatStatus> seatStatusEnum;

    private User user;

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Enum<SeatStatus> getSeatStatusEnum() {
        return seatStatusEnum;
    }

    public void setSeatStatusEnum(Enum<SeatStatus> seatStatusEnum) {
        this.seatStatusEnum = seatStatusEnum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
