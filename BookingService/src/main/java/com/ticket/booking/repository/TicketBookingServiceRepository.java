package com.ticket.booking.repository;

import com.ticket.booking.exception.BusinessValidationException;
import com.ticket.booking.exception.TicketBookingException;
import com.ticket.booking.exception.TicketNotFound;
import com.ticket.booking.model.Coach;
import com.ticket.booking.model.Ticket;
import com.ticket.booking.model.Train;
import com.ticket.booking.request.TicketPurchaseRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


@Repository
public class TicketBookingServiceRepository {

     List<Ticket> ticketsList = new ArrayList<>();

    Train train = Train.getInstance();

    // Need to handle the concurrency at DB level.
    public synchronized Ticket reserveTicket(TicketPurchaseRequest ticketPurchaseRequest) throws TicketBookingException {
            Coach availableCoach;
            try {
                availableCoach = train.getCoachList().stream().filter(coach -> coach.getAvailableSeats() > 0)
                        .findFirst().get();
            } catch (NoSuchElementException e) {
                throw new TicketBookingException("Ticket is not available");
            }

            //2. Reserve Seat and update it
            availableCoach.setAvailableSeats(availableCoach.getAvailableSeats() - 1);
            availableCoach.setTotalBookedSeats(availableCoach.getTotalBookedSeats() + 1);
            Integer seatNumber = availableCoach.getTotalBookedSeats();
            //Update the seat number from AVAILABLE to RESERVED.


            //3. Create Ticket
            Ticket ticket = new Ticket();
            ticket.setTicketNumer(String.valueOf(ThreadLocalRandom.current().nextLong(100,200)));

            ticket.setCoachName(availableCoach.getCoachName());
            ticket.setSeatNumber(seatNumber);
            ticket.setTrainNumber(train.getTrainNumber());
            ticket.setTicketPrice(ticketPurchaseRequest.getTicketFare());
            ticket.setUser(ticketPurchaseRequest.getUser());
            ticket.setSourceStation(ticketPurchaseRequest.getSourceStation());
            ticket.setEndStation(ticketPurchaseRequest.getEndStation());
            ticket.setStatus("RESERVED");

            // This is just to store the tickets in-memory for GET API
            ticketsList.add(ticket);

            return ticket;
    }

    public Ticket fetchReceiptDetail(String userId) throws TicketNotFound {
        try {
            Ticket ticketFetched = ticketsList.stream().filter(
                    ticket -> ticket.getUser().getUserId().equals(userId.trim())).findFirst().get();
            return ticketFetched;
        } catch (NoSuchElementException e) {
            throw new TicketNotFound("No Ticket found for the user");
        }
    }

    public synchronized  Ticket modifySeat(Ticket ticket) throws TicketBookingException {
        Coach availableCoach;
        try {
            availableCoach = train.getCoachList().stream().filter(coach -> coach.getAvailableSeats() > 0)
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            throw new TicketBookingException("Ticket is not available");
        }
        ticket.setSeatNumber(availableCoach.getTotalBookedSeats()+1);
        //Update the OLD ticket to AVAILABLE state
        return  ticket;
    }
    public List<Coach> getAvailableCoaches(){
        return train.getCoachList().stream().filter(coach -> coach.getAvailableSeats()>0)
                .collect(Collectors.toList());
    }
    public boolean isTicketAvailable(){
        return !CollectionUtils.isEmpty(this.getAvailableCoaches());
    }
}
