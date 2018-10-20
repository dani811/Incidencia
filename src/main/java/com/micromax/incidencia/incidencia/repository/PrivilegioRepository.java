package com.micromax.incidencia.incidencia.repository;

import com.micromax.incidencia.incidencia.domain.Privilegio;
import org.springframework.data.repository.CrudRepository;

public interface PrivilegioRepository extends CrudRepository<Privilegio, Long> {

    public Privilegio findByNombre(String name);
}
