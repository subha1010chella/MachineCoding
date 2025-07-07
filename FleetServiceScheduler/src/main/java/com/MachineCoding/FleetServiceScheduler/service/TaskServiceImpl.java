package com.MachineCoding.FleetServiceScheduler.service;

import com.MachineCoding.FleetServiceScheduler.dto.TaskDTO;
import com.MachineCoding.FleetServiceScheduler.model.Task;
import com.MachineCoding.FleetServiceScheduler.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void markAsCompleted(Long taskId) {
        try {
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new RuntimeException("Task not found!"));
            if (task.getCompleted()) throw new RuntimeException("Task already completed");
            task.setCompleted(true);
            taskRepository.save(task);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error while marking task as completed: " + e.getMessage(), e);
        }
    }

    @Override
    public List<TaskDTO> getAllTasksBetweenDateRange(LocalDate startDate, LocalDate endDate) {
        try {
            List<Task> tasks = taskRepository.findByScheduledDateBetween(startDate, endDate);
            if (tasks == null || tasks.isEmpty()) {
                throw new RuntimeException("No Tasks found!");
            }
            return tasks.stream().map(TaskDTO::new).toList();
        } catch (Exception e) {
            throw new RuntimeException("Error while getting tasks between the given range: " + e.getMessage(), e);
        }
    }

    @Override
    public Page<TaskDTO> getAllPendingTasksOf(Long vehicleId, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("scheduledDate").ascending());
            Page<Task> pendingTasks = taskRepository.findAllPendingTasksByVehicleId(vehicleId, pageable);

            if (pendingTasks == null || pendingTasks.isEmpty()) throw new NullPointerException("No Tasks found!");

            return pendingTasks.map(TaskDTO::new);
        } catch (Exception e) {
            throw new RuntimeException("Error while getting tasks that are pending: " + e.getMessage(), e);
        }
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(TaskDTO::new)
                .toList();
    }

}
