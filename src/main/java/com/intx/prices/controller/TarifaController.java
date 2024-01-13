package com.intx.prices.controller;

import com.intx.prices.model.Tarifa;
import com.intx.prices.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarifas")
public class TarifaController {
    @Autowired
    private TarifaRepository tarifaRepository;

    @GetMapping
    public ResponseEntity<Tarifa> obtenerTarifa(
            @RequestParam LocalDateTime fechaAplicacion,
            @RequestParam Long idProducto,
            @RequestParam Long idCadena) {

        Optional<Tarifa> tarifaOptional = tarifaRepository
                .findByFechaAplicacionAndIdProductoAndIdCadena(fechaAplicacion, idProducto, idCadena);

        if (tarifaOptional.isPresent()) {
            return ResponseEntity.ok(tarifaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
