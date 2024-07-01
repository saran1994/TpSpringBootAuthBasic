package com.kalanso.coaching.Controller;


import com.kalanso.coaching.Model.Priority;
import com.kalanso.coaching.Service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/priorities")
public class PriorityController {

    @Autowired
    private PriorityService priorityService;

    @GetMapping
    public List<Priority> getAllPriorities() {
        return priorityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Priority> getPriorityById(@PathVariable Long id) {
        Optional<Priority> priority = priorityService.findById(id);
        if (priority.isPresent()) {
            return ResponseEntity.ok(priority.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Priority createPriority(@RequestBody Priority priority) {
        return priorityService.save(priority);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Priority> updatePriority(@PathVariable Long id, @RequestBody Priority priorityDetails) {
        Optional<Priority> priority = priorityService.findById(id);
        if (priority.isPresent()) {
            Priority updatedPriority = priority.get();
            updatedPriority.setName(priorityDetails.getName());
            return ResponseEntity.ok(priorityService.save(updatedPriority));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriority(@PathVariable Long id) {
        priorityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

