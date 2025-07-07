package com.MachineCoding.FleetServiceScheduler.service;

import com.MachineCoding.FleetServiceScheduler.dto.TaskDTO;
import com.MachineCoding.FleetServiceScheduler.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    VehicleDTO registerVehicle(VehicleDTO vehicleDTO);
    void scheduleTask(TaskDTO task);
    List<VehicleDTO> getAllVehicles();
    void registerVehicles(List<VehicleDTO> vehicleDTO);
    void scheduleTasks(Long vehicleId, List<TaskDTO> taskDTOs);
    VehicleDTO getVehicle(Long id);
}
