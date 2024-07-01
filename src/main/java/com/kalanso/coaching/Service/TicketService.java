package com.kalanso.coaching.Service;



import com.kalanso.coaching.Model.OurUser;
import com.kalanso.coaching.Model.Ticket;
import com.kalanso.coaching.Model.TicketStatus;
import com.kalanso.coaching.Repository.OurUserRepo;
import com.kalanso.coaching.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private OurUserRepo ourUserRepo;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // Create



    public Ticket createTicket(Ticket ticket) {
        OurUser user = getLoggedInUser();
        if (user == null) {
            throw new RuntimeException("Cet utilisateur n'existe pas");
        }
        ticket.setUser(user);

        ticket.setStatus(TicketStatus.En_attente);
        serviceNotification.sendEmailNotification(ticket.getUser().getEmail(), ticket.getDescription());
        return ticketRepository.save(ticket);
    }

    private OurUser getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            return ourUserRepo.findByEmail(username);
        }
        return null;
    }


    // Update
    public Ticket updateTicket(Long id, Ticket ticketDetails) {
        OurUser user = getLoggedInUser();
        if (user == null) {
            throw new RuntimeException("Cet utilisateur n'existe pas");
        }

        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketDetails.getId());
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            if (!ticket.getUser().getId().equals(user.getId())) {
                throw new RuntimeException("Utilisateur non autoriser");
            }
            ticket.setTitle(ticketDetails.getTitle());
            ticket.setDescription(ticketDetails.getDescription());
            ticket.setCategory(ticketDetails.getCategory());
            ticket.setPriority(ticketDetails.getPriority());
            ticket.setStatus(ticketDetails.getStatus());
            serviceNotification.sendEmailNotification(ticket.getUser().getEmail(), ticket.getDescription());
            return ticketRepository.save(ticket);
        } else {
            throw new RuntimeException("Ticket non trouver");
        }
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

