package com.kalanso.coaching.Controller;

import com.kalanso.coaching.Model.OurUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.kalanso.coaching.Service.UserService;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String goHome(){
        return "Bienvenue";
    }

    @PostMapping("/user/save")
    public ResponseEntity<Object> saveUser(@RequestBody OurUser ourUser){
        OurUser result = userService.saveUser(ourUser);
        if (result.getId() > 0){
            return ResponseEntity.ok("Utilisateur inscrit avec succes");
        }
        return ResponseEntity.status(404).body("Error, inscription de l'utilisateur echoué");
    }



    @GetMapping("/user/all")
    public ResponseEntity<Object> getAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/user/single")

    public ResponseEntity<Object> getMyDetails(){
        return ResponseEntity.ok(userService.findByEmail(getLoggedInUserDetails().getUsername()));
    }

    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}

