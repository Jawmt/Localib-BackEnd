package com.m2iformation.ecf.Localib.vehicule;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("vehicules")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VehiculeController {

    private VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService){
        this.vehiculeService = vehiculeService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicule>> findAll() {
        List<Vehicule> vehicules = this.vehiculeService.findAll();
        if(vehicules.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Vehicule> find(@PathVariable("id") Long id) {
        Optional<Vehicule> vehicule = this.vehiculeService.findById(id);
        return new ResponseEntity<>(vehicule.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Vehicule> save(@RequestBody Vehicule entity) {
        Vehicule vehicule = vehiculeService.save(entity);
        return new ResponseEntity<>(vehicule,HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        vehiculeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<Vehicule> modifierVehiculeById(
            @PathVariable("id") Long id,
            @RequestBody Vehicule entity
    ){
        return new ResponseEntity<>(vehiculeService.modifierVehicule(entity, id),HttpStatus.OK);
    }
}
