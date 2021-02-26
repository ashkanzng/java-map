package com.devlon.services;

import com.devlon.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService {

    @Autowired
    StationRepository stationRepository;
}
