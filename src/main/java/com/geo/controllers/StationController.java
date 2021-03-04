package com.geo.controllers;

import com.geo.models.Station;
import com.geo.services.StationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
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
    public List<Station> all() {
        return stationService.findAll();
    }

    @PostMapping("/add/{company_id}")
    public ResponseEntity<Object> add(@PathVariable Integer company_id, @RequestBody Station station) {
        try {
            return ResponseEntity.ok(stationService.add(company_id, station));
        } catch (EntityNotFoundException entityNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
        }
    }

    @PostMapping("/update/{station_id}")
    public ResponseEntity<Object> update(@PathVariable Integer station_id, @RequestBody Station station) {
        try {
            return ResponseEntity.ok(stationService.update(station_id, station));
        } catch (EntityNotFoundException entityNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> removeStation(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(stationService.delete(id));
        } catch (EntityNotFoundException entityNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    /**
     * int R = 6_371;
     * double dis = Math.acos(Math.sin(Math.toRadians(station.getLatitude())) * Math.sin(Math.toRadians(lat)) + Math.cos(Math.toRadians(station.getLatitude())) * Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(station.getLongitude()) - Math.toRadians(lon))) * R;
     */
    @GetMapping(value = "/find")
    public ResponseEntity<Object> nearestStation(@RequestParam(name = "lat") double lat, @RequestParam(name = "lon") double lon, @RequestParam(name = "rad") double rad) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            List<String> stations = stationService.findClosestStations(lat,lon,rad);
            JsonNode result = mapper.readValue(stations.toString(), JsonNode.class);
            return ResponseEntity.ok(result);
        }catch (JsonProcessingException jsonProcessingException){
            return ResponseEntity.badRequest().body(jsonProcessingException.getMessage());
        }
    }

}
