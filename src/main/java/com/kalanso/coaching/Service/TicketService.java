package com.kalanso.coaching.Service;

import com.kalanso.coaching.Model.OurUser;
import com.kalanso.coaching.Model.Ticket;
import com.kalanso.coaching.Repository.OurUserRepo;
import com.kalanso.coaching.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;


}

