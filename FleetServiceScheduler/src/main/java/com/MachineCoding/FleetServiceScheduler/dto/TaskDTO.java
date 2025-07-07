package com.MachineCoding.FleetServiceScheduler.dto;

import com.MachineCoding.FleetServiceScheduler.model.Task;

import java.time.LocalDate;

public class TaskDTO {
    public Long id;
    public Long vehicle_id;
    public String taskDescription;
    public LocalDate scheduledDate;
    public Boolean completed;

    public TaskDTO(Task task) {
        id = task.getId();
        vehicle_id = task.getVehicle().getId();
        taskDescription = task.getTaskDescription();
        scheduledDate = task.getScheduledDate();
        completed = task.getCompleted();
    }

    public TaskDTO() {}
}
