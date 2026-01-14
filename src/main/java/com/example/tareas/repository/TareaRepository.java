package com.example.tareas.repository;

import com.example.tareas.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByFinalizada(Boolean finalizada);

    List<Tarea> findByTextoContainingIgnoreCase(String texto);
}
