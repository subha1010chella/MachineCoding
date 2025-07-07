package com.MachineCoding.FleetServiceScheduler.repository;

import com.MachineCoding.FleetServiceScheduler.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.vehicle.id = :vehicleId AND t.completed = false")
    Page<Task> findAllPendingTasksByVehicleId(@Param("vehicleId") Long vehicleId, Pageable pageable);

    List<Task> findByScheduledDateBetween(LocalDate start, LocalDate end);

    @Query("SELECT t FROM Task t WHERE t.vehicle.id = :vehicleId AND t.scheduledDate = :givenDate")
    Task findTaskByVehicleAndDate(@Param("vehicleId") Long vehicleId,
                                  @Param("givenDate") LocalDate givenDate);
}
