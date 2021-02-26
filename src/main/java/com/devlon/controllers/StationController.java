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
//    public void nearestStation(@PathVariable double lat,@PathVariable double lon , @PathVariable int rad){
    public void nearestStation(){
        double lat1 = 35.746599274497235 , lon1 = 51.30860423202725,
                lat2 = 35.7431074438013 , lon2 = 51.30782639141293;
        int R = 6_371;

//        BigDecimal lat1 = new BigDecimal("35.746599274497235");
//        BigDecimal lat2 = new BigDecimal("35.7431074438013");
//        BigDecimal lon1 = new BigDecimal("51.30860423202725");
//        BigDecimal lon2 = new BigDecimal("51.30782639141293");

        double dis = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2)) * R;
        System.out.println(dis);
    }
}
