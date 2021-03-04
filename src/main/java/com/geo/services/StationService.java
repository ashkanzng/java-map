package com.geo.services;

import com.geo.models.Company;
import com.geo.models.Station;
import com.geo.repositories.CompanyRepository;
import com.geo.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StationService {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    CompanyRepository companyRepository;

    public List<Station> findAll(){
        return stationRepository.findAll();
    }

    public Station add(int companyId,Station station) {
        Company company = companyRepository.findById(companyId).orElseThrow(()-> new EntityNotFoundException("Company not found"));
        station.setCompany(company);
        return stationRepository.save(station);
    }

    public Station update(int stationId, Station station){
        Station existingStation = stationRepository.findById(stationId).orElseThrow(()-> new EntityNotFoundException("Station not found"));
        station.setStationId(existingStation.getId());
        station.setCreated_at(existingStation.getCreated_at());
        station.setCompany(existingStation.getCompany());
        stationRepository.save(station);
        return station;
    }

    public boolean delete(int id) {
        Station existingStation = stationRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Company not found"));
        stationRepository.delete(existingStation);
        return true;
    }

    public List<String> findClosestStations(double lat, double lon, double rad){
        return stationRepository.findAllStationsOrderByDistance(lat,lon,rad);
    }
}
