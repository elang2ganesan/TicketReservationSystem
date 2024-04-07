package com.ticket.booking.service;

import com.ticket.booking.exception.BusinessValidationException;
import com.ticket.booking.exception.PaymentProcessingException;
import com.ticket.booking.exception.TicketBookingException;
import com.ticket.booking.exception.TicketNotFound;
import com.ticket.booking.model.Ticket;
import com.ticket.booking.request.TicketModifyRequest;
import com.ticket.booking.request.TicketPurchaseRequest;

public interface TicketBookingSerivce {
    Ticket purchaseTicket(TicketPurchaseRequest ticketPurchaseRequest) throws PaymentProcessingException, TicketBookingException;
    Ticket fetchReceiptDetail(String userId) throws TicketNotFound, BusinessValidationException;
    Ticket modifySeat(TicketModifyRequest ticketModifyRequest) throws TicketNotFound, BusinessValidationException, TicketBookingException;
}
