package com.MachineCoding.FleetServiceScheduler.controller;

import com.MachineCoding.FleetServiceScheduler.dto.TaskDTO;
import com.MachineCoding.FleetServiceScheduler.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<String> completeTask(@PathVariable Long taskId) {
        try {
            taskService.markAsCompleted(taskId);
            return ResponseEntity.ok("Task marked as Completed");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<TaskDTO>> getTasksInRange(@RequestParam("startDate") LocalDate start, @RequestParam("endDate") LocalDate end) {
        try {
            List<TaskDTO> tasks = taskService.getAllTasksBetweenDateRange(start, end);
            return ResponseEntity.ok(tasks);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(List.of());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

}
