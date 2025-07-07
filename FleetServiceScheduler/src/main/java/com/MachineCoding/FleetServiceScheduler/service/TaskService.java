package com.MachineCoding.FleetServiceScheduler.service;

import com.MachineCoding.FleetServiceScheduler.dto.TaskDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    void markAsCompleted(Long taskId);
    List<TaskDTO> getAllTasksBetweenDateRange(LocalDate startDate, LocalDate endDate);
    Page<TaskDTO> getAllPendingTasksOf(Long vehicleId, int page, int size);
    List<TaskDTO> getAllTasks();
}

