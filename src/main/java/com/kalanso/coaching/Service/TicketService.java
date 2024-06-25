package com.kalanso.coaching.Service;



import com.kalanso.coaching.Model.Ticket;
import com.kalanso.coaching.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private  TicketRepository ticketRepository;

    @Autowired
    private  ServiceNotification serviceNotification;


    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // Create
    public Ticket createTicket(Ticket ticket) {
        serviceNotification.sendEmailNotification(ticket.getUser().getEmail(), ticket.getDescription());
        return ticketRepository.save(ticket);
    }


    // Update
    public Ticket updateTicket(Long id, Ticket ticket) {
        ticket.setId(id); // Ensure the ticket ID is set for update
       serviceNotification.sendEmailNotification(ticket.getUser().getEmail(), ticket.getTitle());
        return ticketRepository.save(ticket);
    }

    // Read
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }


    // Delete
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    // Get All Tickets
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}

