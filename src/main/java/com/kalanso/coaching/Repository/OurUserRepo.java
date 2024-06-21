package com.kalanso.coaching.Repository;


import com.kalanso.coaching.Model.OurUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OurUserRepo extends JpaRepository<OurUser, Long> {
    OurUser findByEmail(String email);
}

