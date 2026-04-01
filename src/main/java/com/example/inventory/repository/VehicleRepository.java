package com.example.inventory.repository;

import com.example.inventory.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    List<Vehicle> findByTenantId(String tenantId);
}