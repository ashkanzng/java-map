package com.devlon.repositories;

import com.devlon.models.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station,Integer> {

    // select acos(sin(LATITUDE ) * sin(35.74480428580317) + cos(35.74480428580317) * cos(LATITUDE ) * cos(LONGITUDE - (51.306926141008866))) * 6371  from stations
    //@Query(value = "SELECT acos(sin(LATITUDE) * sin(:lat) + cos(:lat) * cos(LATITUDE) * cos(LONGITUDE - (:lon))) * 6371  FROM stations",nativeQuery = true)
    @Query(value = "SELECT name,acos(sin(RADIANS(LATITUDE)) * sin(RADIANS(:lat)) + cos(RADIANS(:lat)) * cos(RADIANS(LATITUDE)) * cos(RADIANS(LONGITUDE) - (RADIANS(:lon)))) * 6371  FROM stations",nativeQuery = true)
    List<String> findAllStationsOrderByDistance(
            @Param("lat") Double lat,
            @Param("lon") Double lon
            );

}
