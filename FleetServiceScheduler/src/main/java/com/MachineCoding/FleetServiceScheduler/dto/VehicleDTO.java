package com.MachineCoding.FleetServiceScheduler.dto;

import com.MachineCoding.FleetServiceScheduler.model.Types;
import com.MachineCoding.FleetServiceScheduler.model.Vehicle;
import lombok.Getter;

@Getter
public class VehicleDTO {
    public Long id;
    public String registrationNumber;
    public Types type;

    public VehicleDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        registrationNumber = vehicle.getRegistrationNumber();
        type = vehicle.getType();
    }

    public VehicleDTO() {}
}
