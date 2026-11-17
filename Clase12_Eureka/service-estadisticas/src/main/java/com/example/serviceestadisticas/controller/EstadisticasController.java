package com.example.serviceestadisticas.controller;

import com.example.serviceestadisticas.client.SesionEstudioClient;
import com.example.serviceestadisticas.model.SesionEstudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Calcula las estadisticas que en el Lab 6 se mostraban como graficos
 * (barras: horas por mes / torta: proporcion por tipo de actividad),
 * pero ahora consumiendo los datos de "service-estudio" via Feign,
 * tal como el patron Calculator -> Addition visto en la Clase 12.
 *
 * Endpoints:
 *  GET /api/estadisticas/horas-por-mes?curso=xxx   (curso es opcional)
 *  GET /api/estadisticas/horas-por-tipo?curso=xxx  (curso es opcional)
 */
@RestController
public class EstadisticasController {

    @Autowired
    private SesionEstudioClient sesionEstudioClient;

    @GetMapping("/api/estadisticas/horas-por-mes")
    public Map<String, Double> horasPorMes(@RequestParam(required = false) String curso) {
        List<SesionEstudio> sesiones = obtenerSesiones(curso);

        return sesiones.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getFecha().getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")),
                        Collectors.summingDouble(SesionEstudio::getHoras)
                ));
    }

    @GetMapping("/api/estadisticas/horas-por-tipo")
    public Map<String, Double> horasPorTipo(@RequestParam(required = false) String curso) {
        List<SesionEstudio> sesiones = obtenerSesiones(curso);

        return sesiones.stream()
                .collect(Collectors.groupingBy(
                        SesionEstudio::getTipo,
                        Collectors.summingDouble(SesionEstudio::getHoras)
                ));
    }

    private List<SesionEstudio> obtenerSesiones(String curso) {
        if (curso == null || curso.isBlank()) {
            return sesionEstudioClient.obtenerTodas();
        }
        return sesionEstudioClient.obtenerPorCurso(curso);
    }
}
