package com.dam.proyectospring.controladores;

import com.dam.proyectospring.modelos.Piloto;
import com.dam.proyectospring.servicios.PilotoServicio;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class APIController {
    @Autowired
    private PilotoServicio pilotoServicio;

    // GET de todos los pilotos
    @GetMapping("/api/pilotos")
    public ResponseEntity<List<Piloto>> getAllPilotos() {
        List<Piloto> pilotos = pilotoServicio.findAllPilotos();
        return new ResponseEntity<>(pilotos, HttpStatus.OK);
    }

    // GET de un piloto por ID
    @GetMapping("/api/pilotos/{id}")
    public ResponseEntity<Piloto> getPiloto(@PathVariable String id) {
        return pilotoServicio.findPilotoById(id)
                .map(piloto -> new ResponseEntity<>(piloto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST de un piloto
    @PostMapping("/api/pilotos")
    public ResponseEntity<Piloto> addPiloto(@RequestBody Piloto piloto) {
        Piloto nuevoPiloto = pilotoServicio.savePiloto(piloto);
        return new ResponseEntity<>(nuevoPiloto, HttpStatus.CREATED);
    }

    // PUT de un piloto
    @PutMapping("/api/pilotos/{id}")
    public ResponseEntity<Piloto> updatePiloto(@PathVariable String id, @RequestBody Piloto piloto) {
        return pilotoServicio.updatePiloto(id, piloto)
                .map(pilotoActualizado -> new ResponseEntity<>(pilotoActualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE de un piloto
    @DeleteMapping("/api/pilotos/{id}")
    public ResponseEntity<Void> deletePiloto(@PathVariable String id) {
        boolean eliminado = pilotoServicio.deletePilotoById(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
