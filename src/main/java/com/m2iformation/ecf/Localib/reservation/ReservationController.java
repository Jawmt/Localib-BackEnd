package com.m2iformation.ecf.Localib.reservation;

import com.m2iformation.ecf.Localib.vehicule.Vehicule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping("reservations")
    public ResponseEntity<List<Reservation>> findAll(){
        List<Reservation> reservations = reservationService.findAll();
        if(reservations.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping("{idClient}/reservation/{idVehicule}")
    public ResponseEntity<Reservation> save(
            @PathVariable(value = "idClient") Long idClient,
            @PathVariable(value = "idVehicule") Long idVehicule,
            @RequestBody Reservation reservation
            ){
        Reservation reservationSauv = reservationService.save(idClient, idVehicule, reservation);

        return new ResponseEntity<>(reservationSauv,HttpStatus.CREATED);
    }


    @DeleteMapping("reservation/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        reservationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("reservation/{id}")
    public ResponseEntity<Reservation> save(
            @PathVariable("id") Long id,
            @RequestBody Reservation reservation
    ){
        Reservation reservationModifier = reservationService.modifierReservation(reservation, id);
        return new ResponseEntity<>(reservationModifier,HttpStatus.OK);
    }


}
