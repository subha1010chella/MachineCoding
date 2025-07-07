package com.MachineCoding.FleetServiceScheduler.repository;

import com.MachineCoding.FleetServiceScheduler.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
