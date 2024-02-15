package com.dam.proyectospring.controladores;

import com.dam.proyectospring.modelos.Piloto;
import com.dam.proyectospring.servicios.PilotoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pilotos")
public class PilotoController {

    @Autowired
    private PilotoServicio pilotoService;

    // GET: Obtener todos los pilotos
    @GetMapping
    public ResponseEntity<List<Piloto>> getAllPilotos() {
        return ResponseEntity.ok(pilotoService.findAllPilotos());
    }

    // POST: Crear un nuevo piloto
    @PostMapping
    public ResponseEntity<Piloto> createPiloto(@RequestBody Piloto piloto) {
        Piloto nuevoPiloto = pilotoService.savePiloto(piloto);
        return ResponseEntity.ok(nuevoPiloto);
    }

    // GET: Obtener un piloto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Piloto> getPilotoById(@PathVariable String id) {
        return pilotoService.findPilotoById(id)
                .map(piloto -> ResponseEntity.ok(piloto))
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Actualizar un piloto por ID
    @PutMapping("/{id}")
    public ResponseEntity<Piloto> updatePiloto(@PathVariable String id, @RequestBody Piloto piloto) {
        return pilotoService.updatePiloto(id, piloto)
                .map(pilotoActualizado -> ResponseEntity.ok(pilotoActualizado))
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Eliminar un piloto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePiloto(@PathVariable String id) {
        boolean eliminado = pilotoService.deletePilotoById(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


