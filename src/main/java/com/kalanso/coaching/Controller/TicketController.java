package com.kalanso.coaching.Controller;


import com.kalanso.coaching.Model.Ticket;
import com.kalanso.coaching.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Endpoint

    @PostMapping("/creer")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }
    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    // Endpoint recuperer par id
    @GetMapping("/{id}")
    public Optional<Ticket> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    // Endpoint modifier
    @PutMapping("edit/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ticketService.updateTicket(id, ticket);
    }

    // Endpoint supprimer
    @DeleteMapping("delete/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }

    // Endpoint recuperer liste
    @GetMapping("/liste")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }
}

