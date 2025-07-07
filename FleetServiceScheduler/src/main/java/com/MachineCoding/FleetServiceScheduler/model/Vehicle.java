package com.MachineCoding.FleetServiceScheduler.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    private Types type;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public Vehicle(String registrationNumber, Types type) {
        this.registrationNumber = registrationNumber;
        this.type = type;
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.setVehicle(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setVehicle(null);
    }
}
