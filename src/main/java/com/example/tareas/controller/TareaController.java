package com.example.tareas.controller;

import com.example.tareas.entity.Tarea;
import com.example.tareas.service.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TareaController {

    private final TareaService tareaService;

    @GetMapping
    public ResponseEntity<List<Tarea>> obtenerTodasLasTareas() {
        List<Tarea> tareas = tareaService.obtenerTodasLasTareas();
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerTareaPorId(@PathVariable Long id) {
        return tareaService.obtenerTareaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        Tarea nuevaTarea = tareaService.crearTarea(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTarea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        try {
            Tarea tareaActualizada = tareaService.actualizarTarea(id, tarea);
            return ResponseEntity.ok(tareaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        try {
            tareaService.eliminarTarea(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estado/{finalizada}")
    public ResponseEntity<List<Tarea>> obtenerTareasPorEstado(@PathVariable Boolean finalizada) {
        List<Tarea> tareas = tareaService.obtenerTareasPorEstado(finalizada);
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Tarea>> buscarTareasPorTexto(@RequestParam String texto) {
        List<Tarea> tareas = tareaService.buscarTareasPorTexto(texto);
        return ResponseEntity.ok(tareas);
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<Tarea> marcarComoFinalizada(@PathVariable Long id) {
        try {
            Tarea tarea = tareaService.marcarComoFinalizada(id);
            return ResponseEntity.ok(tarea);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
