package com.dam.proyectospring.servicios;

import com.dam.proyectospring.modelos.Piloto;
import com.dam.proyectospring.repositorios.PilotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class PilotoServicioImpl implements PilotoServicio {


    @Autowired
    private PilotoRepositorio pilotoRepositorio;

    @Override
    public List<Piloto> findAllPilotos() {
        return pilotoRepositorio.findAll();
    }

    @Override
    public Piloto savePiloto(Piloto piloto) {
        return pilotoRepositorio.save(piloto);
    }

    @Override
    public Optional<Piloto> findPilotoById(String id) {
        return pilotoRepositorio.findById(id);
    }

    @Override
    public Optional<Piloto> updatePiloto(String id, Piloto piloto) {
        return pilotoRepositorio.findById(id)
                .map(pilotoExistente -> {
                    piloto.setId(pilotoExistente.getId());
                    return pilotoRepositorio.save(piloto);
                });
    }

    @Override
    public boolean deletePilotoById(String id) {
        if (pilotoRepositorio.existsById(id)) {
            pilotoRepositorio.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
