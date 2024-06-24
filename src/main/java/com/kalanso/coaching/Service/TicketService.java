package com.kalanso.coaching.Service;


import com.kalanso.coaching.Model.Ticket;
import com.kalanso.coaching.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public ResponseEntity<Ticket> updateTicket(Long id, Ticket ticketDetails) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket existingTicket = optionalTicket.get();
            existingTicket.setTitle(ticketDetails.getTitle());
            existingTicket.setDescription(ticketDetails.getDescription());
            existingTicket.setCategory(ticketDetails.getCategory());
            existingTicket.setPriority(ticketDetails.getPriority());
            existingTicket.setStatus(ticketDetails.getStatus());
            existingTicket.setUser(ticketDetails.getUser());
            Ticket updatedTicket = ticketRepository.save(existingTicket);
            return ResponseEntity.ok(updatedTicket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteTicket(Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticketRepository.delete(ticket);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
