package com.example.inventory.controller;

import com.example.inventory.repository.DealerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@RestController
@RequestMapping("/admin")
public class AdminController {

    private final DealerRepository repo;

    public AdminController(DealerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/dealers/countBySubscription")
    public Map<String, Long> count() {
        Map<String, Long> result = new HashMap<>();
        result.put("BASIC", repo.countBySubscriptionType("BASIC"));
        result.put("PREMIUM", repo.countBySubscriptionType("PREMIUM"));
        return result;
    }
}