package com.example.serviceestudio.controller;

import com.example.serviceestudio.model.SesionEstudio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Controlador CRUD para las sesiones de estudio.
 * Guarda los datos en memoria (simula lo que en el Lab 6 vivia en Cloud Firestore).
 *
 * Endpoints:
 *  GET    /api/sesiones                 -> listar todas
 *  GET    /api/sesiones/{id}            -> obtener una
 *  GET    /api/sesiones/curso/{curso}   -> filtrar por curso
 *  POST   /api/sesiones                 -> crear
 *  PUT    /api/sesiones/{id}            -> editar
 *  DELETE /api/sesiones/{id}            -> eliminar
 */
@RestController
@RequestMapping("/api/sesiones")
public class SesionEstudioController {

    private final Map<Long, SesionEstudio> sesiones = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public SesionEstudioController() {
        // Datos de ejemplo, igual a la captura del Lab 6 (tipos con tilde, igual que Tipos.java del repo)
        guardar(new SesionEstudio(null, "Servicios y Aplicaciones para IoT", LocalDate.of(2026, 10, 28), 2.0, "Laboratorio"));
        guardar(new SesionEstudio(null, "Redes de Computadoras", LocalDate.of(2026, 10, 27), 1.5, "Teoría"));
        guardar(new SesionEstudio(null, "Comunicaciones Digitales", LocalDate.of(2026, 10, 25), 3.0, "Práctica"));
        guardar(new SesionEstudio(null, "Servicios y Aplicaciones para IoT", LocalDate.of(2026, 10, 22), 1.0, "Teoría"));
    }

    private SesionEstudio guardar(SesionEstudio sesion) {
        long id = idGenerator.incrementAndGet();
        sesion.setId(id);
        sesiones.put(id, sesion);
        return sesion;
    }

    @GetMapping
    public List<SesionEstudio> listarTodas() {
        return sesiones.values().stream()
                .sorted((a, b) -> b.getFecha().compareTo(a.getFecha()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SesionEstudio> obtener(@PathVariable Long id) {
        SesionEstudio sesion = sesiones.get(id);
        if (sesion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sesion);
    }

    @GetMapping("/curso/{curso}")
    public List<SesionEstudio> listarPorCurso(@PathVariable String curso) {
        return sesiones.values().stream()
                .filter(s -> s.getCurso().equalsIgnoreCase(curso))
                .sorted((a, b) -> b.getFecha().compareTo(a.getFecha()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<SesionEstudio> crear(@RequestBody SesionEstudio nueva) {
        SesionEstudio creada = guardar(nueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SesionEstudio> editar(@PathVariable Long id, @RequestBody SesionEstudio actualizada) {
        if (!sesiones.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        actualizada.setId(id);
        sesiones.put(id, actualizada);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!sesiones.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        sesiones.remove(id);
        return ResponseEntity.noContent().build();
    }
}
