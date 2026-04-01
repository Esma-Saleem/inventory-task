package com.example.inventory.controller;

import com.example.inventory.entity.Vehicle;
import com.example.inventory.repository.VehicleRepository;
import com.example.inventory.config.TenantContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleRepository repo;

    public VehicleController(VehicleRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Vehicle create(@RequestBody Vehicle vehicle) {
        vehicle.setTenantId(TenantContext.getTenantId());
        return repo.save(vehicle);
    }

    @GetMapping
    public List<Vehicle> getAll() {
        return repo.findByTenantId(TenantContext.getTenantId());
    }
}