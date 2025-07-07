package com.MachineCoding.FleetServiceScheduler.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskDescription;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate scheduledDate;

    private Boolean completed;

    @ManyToOne()
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Task(String taskDescription, LocalDate scheduledDate) {
        this.taskDescription = taskDescription;
        this.scheduledDate = scheduledDate;
        this.completed = false;
    }
}
