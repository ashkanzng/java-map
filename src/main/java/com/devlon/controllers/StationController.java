package com.devlon.controllers;

import com.devlon.models.Station;
import com.devlon.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@CrossOrigin(allowCredentials = "false", origins = "*", allowedHeaders = "*", maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}
)
@RequestMapping(value = "/api/station")
@RestController
public class StationController {

    @Autowired
    StationService stationService;

    @GetMapping("/all")
    public List<Station> all(){
        return stationService.findAll();
    }

    @PostMapping("/add/{company_id}")
    public ResponseEntity add(@PathVariable Integer company_id,@RequestBody Station station){
        try{
            return ResponseEntity.ok().body(stationService.add(company_id,station));
        }catch (EntityNotFoundException entityNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
        }
    }

    @PostMapping("/update/{station_id}")
    public ResponseEntity update(@PathVariable Integer station_id,@RequestBody Station station){
        try{
            return ResponseEntity.ok(stationService.update(station_id,station));
        }catch (EntityNotFoundException entityNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity removeStation(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok().body(stationService.delete(id));
        }catch (EntityNotFoundException entityNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @GetMapping(value = "/nearest_stations")
    public void nearestStation(@RequestParam(name = "lat") double lat,@RequestParam(name = "lon") double lon , @RequestParam(name = "rad") int rad){

        int R = 6_371;
        stationService.findClosestStations(lat, lon, rad).forEach(station -> {
            double dis = Math.acos(Math.sin(Math.toRadians(station.getLatitude())) * Math.sin(Math.toRadians(lat)) + Math.cos(Math.toRadians(station.getLatitude())) * Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(station.getLongitude()) - Math.toRadians(lon))) * R;
            System.out.println(station.getName() + " " + dis);
        });
    }


}
