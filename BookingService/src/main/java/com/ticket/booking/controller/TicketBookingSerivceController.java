package com.ticket.booking.controller;

import com.ticket.booking.exception.BusinessValidationException;
import com.ticket.booking.exception.PaymentProcessingException;
import com.ticket.booking.exception.TicketBookingException;
import com.ticket.booking.exception.TicketNotFound;
import com.ticket.booking.model.Ticket;
import com.ticket.booking.request.TicketModifyRequest;
import com.ticket.booking.request.TicketPurchaseRequest;
import com.ticket.booking.response.ResponseHandler;
import com.ticket.booking.service.TicketBookingSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/train/booking")
public class TicketBookingSerivceController {

    @Autowired
    TicketBookingSerivce ticketBookingSerivce;
    private static final Logger logger = LoggerFactory.getLogger(TicketBookingSerivceController.class);

    /**
     * API to purchase a ticket
     * Note : Number of ticket can be booked at a time is 1.
     * //Assumption:
     *         // 1. User is already signed in,
     *         // 2. Available trains and fare details are shown
     *         // 3. User has chosen a particular Train: #1234 and hitting this API with source, destination and user details
     *         // 4. Hence, User is already sign in and shown with valid train details,
     *         // user and train details are not validated in this API.
     * @param ticketPurchaseRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> purchaseTicket(@RequestBody TicketPurchaseRequest ticketPurchaseRequest) {
        try {
            Ticket ticket =  ticketBookingSerivce.purchaseTicket(ticketPurchaseRequest);
            logger.info("Ticet is reserved successfully");
            return ResponseHandler.responseBuilder(
                    "Ticket is reserved successfully", HttpStatus.OK,ticket);
        } catch (PaymentProcessingException e) {
            return ResponseHandler.responseBuilder(
                    e.getMessage(), HttpStatus.PAYMENT_REQUIRED);
        } catch (TicketBookingException e) {
            return ResponseHandler.responseBuilder(
                    e.getMessage(), HttpStatus.OK);
        }
    }

    /**
     * Fetch the Ticket Details by passing userId
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getTicketDetails(@PathVariable("userId")  String userId) {
        try {
            Ticket ticket = ticketBookingSerivce.fetchReceiptDetail(userId);
            logger.info("Ticket detail is fetched successfully");
            return ResponseHandler.responseBuilder(
                    "Ticket detail is fetched successfully", HttpStatus.OK,ticket);
        } catch (BusinessValidationException e) {
            return ResponseHandler.responseBuilder(
                    e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (TicketNotFound e) {
            return ResponseHandler.responseBuilder(
                    e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Object> modifySeat(@RequestBody TicketModifyRequest ticketModifyRequest) {
        try {
            Ticket ticket = ticketBookingSerivce.modifySeat(ticketModifyRequest);
            logger.info("seat Number is modified successfully");
            return ResponseHandler.responseBuilder(
                    "Ticket detail is fetched successfully", HttpStatus.OK,ticket);
        } catch (BusinessValidationException e) {
            return ResponseHandler.responseBuilder(
                    e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (TicketNotFound e) {
            return ResponseHandler.responseBuilder(
                    e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (TicketBookingException e) {
            return ResponseHandler.responseBuilder(
                    e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
