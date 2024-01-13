package com.intx.prices.repository;

import com.intx.prices.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
    Optional<Tarifa> findByFechaAplicacionAndIdProductoAndIdCadena(
            LocalDateTime fechaAplicacion, Long idProducto, Long idCadena);
}

