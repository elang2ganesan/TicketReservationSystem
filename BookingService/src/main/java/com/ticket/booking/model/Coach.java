package com.ticket.booking.model;

import java.util.Map;

public class Coach {

    private String coachName;

    private Integer totalNoOfSeats;

    private Integer availableSeats;

    private Integer totalBookedSeats;

    private Map<Integer, String> seatNumberStatus;

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public Integer getTotalNoOfSeats() {
        return totalNoOfSeats;
    }

    public void setTotalNoOfSeats(Integer totalNoOfSeats) {
        this.totalNoOfSeats = totalNoOfSeats;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getTotalBookedSeats() {
        return totalBookedSeats;
    }

    public void setTotalBookedSeats(Integer totalBookedSeats) {
        this.totalBookedSeats = totalBookedSeats;
    }

    public Map<Integer, String> getSeatNumberStatus() {
        return seatNumberStatus;
    }

    public void setSeatNumberStatus(Map<Integer, String> seatNumberStatus) {
        this.seatNumberStatus = seatNumberStatus;
    }
}
