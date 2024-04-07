package com.ticket.booking.exception;

public class TicketBookingException extends  Exception {

    public TicketBookingException(String message) {
        super(message);
    }

    public TicketBookingException(String message, Throwable cause) {
        super(message, cause);
    }

}
