package com.kalanso.coaching.Service;


import com.kalanso.coaching.Model.OurUser;
import com.kalanso.coaching.Repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private OurUserRepo ourUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public OurUser saveUser(OurUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ourUserRepo.save(user);
    }

    public OurUser findByEmail(String email) {
        return ourUserRepo.findByEmail(email);
    }

    public Iterable<OurUser> findAllUsers() {
        return ourUserRepo.findAll();
    }
}

