package com.dam.proyectospring.servicios;

import com.dam.proyectospring.modelos.Piloto;

import java.util.List;

import java.util.List;
import java.util.Optional;

public interface PilotoServicio {

    // Método para obtener todos los pilotos
    List<Piloto> findAllPilotos();

    // Método para guardar un nuevo piloto
    Piloto savePiloto(Piloto piloto);

    // Método para obtener un piloto por su ID
    Optional<Piloto> findPilotoById(String id);

    // Método para actualizar un piloto
    Optional<Piloto> updatePiloto(String id, Piloto piloto);

    // Método para eliminar un piloto por su ID
    boolean deletePilotoById(String id);
}

