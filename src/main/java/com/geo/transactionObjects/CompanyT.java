package com.geo.transactionObjects;

import com.geo.models.Station;
import lombok.Data;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class CompanyT {

    private String name;
    private List<CompanyT> child;
    private Set<Station> stations;

    {
        this.stations = new HashSet<>();
        this.child = new ArrayList<>();
    }

    public CompanyT(String name) {
        this.name = name;
    }

}
