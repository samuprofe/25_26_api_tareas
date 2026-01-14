package com.example.tareas.service;

import com.example.tareas.entity.Tarea;
import com.example.tareas.repository.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TareaService {

    private final TareaRepository tareaRepository;

    public List<Tarea> obtenerTodasLasTareas() {
        return tareaRepository.findAll();
    }

    public Optional<Tarea> obtenerTareaPorId(Long id) {
        return tareaRepository.findById(id);
    }

    public Tarea crearTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea actualizarTarea(Long id, Tarea tareaActualizada) {
        return tareaRepository.findById(id)
                .map(tarea -> {
                    tarea.setTexto(tareaActualizada.getTexto());
                    tarea.setComentario(tareaActualizada.getComentario());
                    tarea.setFinalizada(tareaActualizada.getFinalizada());
                    return tareaRepository.save(tarea);
                })
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));
    }

    public void eliminarTarea(Long id) {
        if (!tareaRepository.existsById(id)) {
            throw new RuntimeException("Tarea no encontrada con id: " + id);
        }
        tareaRepository.deleteById(id);
    }

    public List<Tarea> obtenerTareasPorEstado(Boolean finalizada) {
        return tareaRepository.findByFinalizada(finalizada);
    }

    public List<Tarea> buscarTareasPorTexto(String texto) {
        return tareaRepository.findByTextoContainingIgnoreCase(texto);
    }

    public Tarea marcarComoFinalizada(Long id) {
        return tareaRepository.findById(id)
                .map(tarea -> {
                    tarea.setFinalizada(true);
                    return tareaRepository.save(tarea);
                })
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));
    }
}
