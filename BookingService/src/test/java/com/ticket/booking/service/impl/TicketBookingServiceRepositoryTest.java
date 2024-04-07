package com.ticket.booking.service.impl;

import com.ticket.booking.exception.BusinessValidationException;
import com.ticket.booking.exception.TicketNotFound;
import com.ticket.booking.model.Ticket;
import com.ticket.booking.model.User;
import com.ticket.booking.repository.TicketBookingServiceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TicketBookingServiceRepositoryTest {

    @InjectMocks
    TicketBookingServiceRepository ticketBookingServiceRepository;


    @Test(expected = BusinessValidationException.class)
    public void testForUserIdIsNull() throws Exception {
        ticketBookingServiceRepository.fetchReceiptDetail(null);
    }

    @Test(expected = TicketNotFound.class)
    public void testForTicketNotFound() throws Exception {
        List<Ticket> ticketList = new ArrayList<>();
        Ticket ticket = new Ticket();
        User user = new User();
        user.setUserId("user-1");
        ticket.setUser(user);
        ticketList.add(ticket);
        ReflectionTestUtils.setField(ticketBookingServiceRepository, "ticketsList", ticketList);
        ticketBookingServiceRepository.fetchReceiptDetail("user-2");
    }

    @Test
    public void testForTicketFound() throws Exception {
        List<Ticket> ticketList = new ArrayList<>();
        Ticket ticket = new Ticket();
        User user = new User();
        user.setUserId("user-1");
        ticket.setUser(user);
        ticket.setTicketNumer("1234");
        ticketList.add(ticket);
        ReflectionTestUtils.setField(ticketBookingServiceRepository, "ticketsList", ticketList);
        Assert.assertEquals(ticket.getTicketNumer(),ticketBookingServiceRepository.fetchReceiptDetail("user-1").getTicketNumer());
    }
}
