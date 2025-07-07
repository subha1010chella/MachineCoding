package com.MachineCoding.FleetServiceScheduler.service;

import com.MachineCoding.FleetServiceScheduler.dto.TaskDTO;
import com.MachineCoding.FleetServiceScheduler.dto.VehicleDTO;
import com.MachineCoding.FleetServiceScheduler.model.Task;
import com.MachineCoding.FleetServiceScheduler.model.Vehicle;
import com.MachineCoding.FleetServiceScheduler.repository.TaskRepository;
import com.MachineCoding.FleetServiceScheduler.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public VehicleDTO registerVehicle(VehicleDTO vehicleDTO) {
        try {
            Vehicle vehicle = new Vehicle(vehicleDTO.registrationNumber, vehicleDTO.type);
            Vehicle saved = vehicleRepository.save(vehicle);
            return new VehicleDTO(saved);
        } catch (Exception e) {
            throw new RuntimeException("Error while registering the vehicle: " + e.getMessage(), e);
        }
    }

    @Override
    public void scheduleTask(TaskDTO task) {
        try {
            if (task.scheduledDate.isBefore(LocalDate.now())) {
                throw new RuntimeException("The given date is before current date");
            } else if (taskRepository.findTaskByVehicleAndDate(task.vehicle_id, task.scheduledDate) != null) {
                throw new RuntimeException("Vehicle already scheduled for a different task on the given date");
            }

            Vehicle vehicle = vehicleRepository.getReferenceById(task.vehicle_id);

            Task taskObject = new Task(task.taskDescription, task.scheduledDate);
            taskObject.setVehicle(vehicle);

            vehicle.addTask(taskObject);

            vehicleRepository.save(vehicle);
        } catch (Exception e) {
            throw new RuntimeException("Error while scheduling the task: " + e.getMessage(), e);
        }
    }

    @Override
    public VehicleDTO getVehicle(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.map(VehicleDTO::new).orElse(null);

//        Vehicle vehicle = vehicleRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
//        return new VehicleDTO(vehicle);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(VehicleDTO::new).toList();
    }

    @Override
    public void registerVehicles(List<VehicleDTO> vehicleDTOs) {
        List<Vehicle> vehicles = vehicleDTOs.stream()
                .map(dto -> new Vehicle(dto.registrationNumber, dto.type))
                .toList();
        vehicleRepository.saveAll(vehicles);
    }

    @Override
    public void scheduleTasks(Long vehicleId, List<TaskDTO> taskDTOs) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        for (TaskDTO taskDTO : taskDTOs) {
            Task task = new Task(taskDTO.taskDescription, taskDTO.scheduledDate);
            task.setVehicle(vehicle);
            vehicle.getTasks().add(task);
        }
        vehicleRepository.save(vehicle);
    }


}
