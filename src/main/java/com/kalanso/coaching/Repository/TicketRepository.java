package com.kalanso.coaching.Repository;

import com.kalanso.coaching.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

}


