package com.geo.repositories;

import com.geo.models.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station,Integer> {

    @Query(value = "SELECT CONCAT('{\"id\":',id,', \"name\":\"',name,'\",\"distance\":',round(distance,3),'}' ) FROM (select id,name,acos(sin(RADIANS(LATITUDE)) * sin(RADIANS(:lat)) + cos(RADIANS(:lat)) * cos(RADIANS(LATITUDE)) * cos(RADIANS(LONGITUDE) - (RADIANS(:lon)))) * 6371  as distance  FROM stations ) " +
            "where distance < :rad ORDER BY distance ASC ",
            nativeQuery = true)
    List<String> findAllStationsOrderByDistance(
            @Param("lat") Double lat,
            @Param("lon") Double lon,
            @Param("rad") Double rad
            );
}
