package com.MachineCoding.FleetServiceScheduler.controller;

import com.MachineCoding.FleetServiceScheduler.dto.TaskDTO;
import com.MachineCoding.FleetServiceScheduler.dto.VehicleDTO;
import com.MachineCoding.FleetServiceScheduler.model.Vehicle;
import com.MachineCoding.FleetServiceScheduler.service.TaskService;
import com.MachineCoding.FleetServiceScheduler.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private TaskService taskService;

    @PostMapping("/register")
    public ResponseEntity<VehicleDTO> registerVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            VehicleDTO saved = vehicleService.registerVehicle(vehicleDTO);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long id) {
        VehicleDTO vehicle = vehicleService.getVehicle(id);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleDTO>> getVehicles() {
        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping("/{vehicleId}/tasks")
    public ResponseEntity<String> scheduleTask(@RequestBody TaskDTO taskDTO) {
        try {
            vehicleService.scheduleTask(taskDTO);
            return ResponseEntity.ok("Task Scheduled Successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{vehicleId}/tasks/pending")
    public ResponseEntity<List<TaskDTO>> getPendingTasks(@PathVariable Long vehicleId, @RequestParam int page, @RequestParam int size) {
        try {
            Page<TaskDTO> tasks = taskService.getAllPendingTasksOf(vehicleId, page, size);
            return ResponseEntity.ok(tasks.getContent());
        } catch (NullPointerException e) {
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register/bulk")
    public String registerVehicles(@RequestBody List<VehicleDTO> vehicleDTOs) {
        vehicleService.registerVehicles(vehicleDTOs);
        return "Vehicles registered successfully";
    }

    @PostMapping("/{vehicleId}/tasks/bulk")
    public String scheduleTasks(@PathVariable Long vehicleId, @RequestBody List<TaskDTO> taskDTOs) {
        vehicleService.scheduleTasks(vehicleId, taskDTOs);
        return "Tasks scheduled successfully";
    }
}
