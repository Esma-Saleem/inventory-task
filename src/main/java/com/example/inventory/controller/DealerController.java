package com.example.inventory.controller;

import com.example.inventory.entity.Dealer;
import com.example.inventory.repository.DealerRepository;
import com.example.inventory.config.TenantContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dealers")
public class DealerController {

    private final DealerRepository repo;

    public DealerController(DealerRepository repo) {
        this.repo = repo;
    }

   @PostMapping
public Dealer create(
        @RequestBody Dealer dealer,
        @RequestHeader("X-Tenant-Id") String tenantId
) {
    dealer.setTenantId(tenantId);
    return repo.save(dealer);
}

@GetMapping
public List<Dealer> getAll(
        @RequestHeader("X-Tenant-Id") String tenantId
) {
    return repo.findByTenantId(tenantId);
}
    @GetMapping("/test")
    public String test() {
        return "Dealer Controller Working";
    }

}