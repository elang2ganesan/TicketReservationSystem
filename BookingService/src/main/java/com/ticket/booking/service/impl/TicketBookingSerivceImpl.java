package com.ticket.booking.service.impl;

import com.ticket.booking.exception.BusinessValidationException;
import com.ticket.booking.exception.PaymentProcessingException;
import com.ticket.booking.exception.TicketBookingException;
import com.ticket.booking.exception.TicketNotFound;
import com.ticket.booking.model.Ticket;
import com.ticket.booking.repository.TicketBookingServiceRepository;
import com.ticket.booking.request.TicketModifyRequest;
import com.ticket.booking.request.TicketPurchaseRequest;
import com.ticket.booking.service.PaymentService;
import com.ticket.booking.service.TicketBookingSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketBookingSerivceImpl implements TicketBookingSerivce {


    @Autowired
    PaymentService paymentService;

    @Autowired
    TicketBookingServiceRepository ticketBookingServiceRepository;

    private static final Logger logger = LoggerFactory.getLogger(TicketBookingSerivceImpl.class);

    @Override
    public Ticket purchaseTicket(TicketPurchaseRequest ticketPurchaseRequest)
            throws PaymentProcessingException, TicketBookingException {

        //1. Check if ticket available
        if(!ticketBookingServiceRepository.isTicketAvailable()) {
            logger.debug("Ticket is not available");
            throw new TicketBookingException("Ticket is not available");
        }

        //2. Payment is success
        if (!paymentService.makePayment()) {
            logger.debug("Payment is failed");
            throw new PaymentProcessingException("Payment is failed");
        }

        //3. Reserve the ticket
        Ticket ticket = ticketBookingServiceRepository.reserveTicket(ticketPurchaseRequest);

        //4. Post-reservation async activities such as email ticket, etc
        //Todo add if needed

        return ticket;
    }

    @Override
    public Ticket fetchReceiptDetail(String userId) throws TicketNotFound, BusinessValidationException {
        return ticketBookingServiceRepository.fetchReceiptDetail(userId);
    }

    @Override
    public Ticket modifySeat(TicketModifyRequest ticketModifyRequest) throws TicketNotFound, BusinessValidationException, TicketBookingException {

        //1. Fetch Ticket Details
        Ticket ticket = this.fetchReceiptDetail(ticketModifyRequest.getUserId());

        //2. Modify Seat Number
        return ticketBookingServiceRepository.modifySeat(ticket);
    }
}
