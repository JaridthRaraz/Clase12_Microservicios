package com.example.serviceestadisticas.client;

import com.example.serviceestadisticas.model.SesionEstudio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Cliente Feign hacia el microservicio "service-estudio".
 * El "name" debe coincidir con spring.application.name de ese servicio
 * (asi lo resuelve Eureka), exactamente igual al patron
 * ServiceAdditionClient -> "Clase12-EurekaClient-Addition" visto en clase.
 */
@FeignClient(name = "service-estudio")
public interface SesionEstudioClient {

    @GetMapping("/api/sesiones")
    List<SesionEstudio> obtenerTodas();

    @GetMapping("/api/sesiones/curso/{curso}")
    List<SesionEstudio> obtenerPorCurso(@PathVariable("curso") String curso);
}
