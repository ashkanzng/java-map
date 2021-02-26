package com.devlon.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "stations")
public class Station {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    @Column(name = "name", columnDefinition = "varchar(64)")
    private String name;

    @Getter
    @Setter
    @Column(name = "latitude", columnDefinition = "Decimal(10,12)")
    private Double latitude;

    @Getter
    @Setter
    @Column(name = "longitude", columnDefinition = "Decimal(10,12)")
    private Double longitude;

    @JsonIgnore
    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id", columnDefinition = "int(11)", nullable = false)
    private Company company;

    @JsonIgnore
    @Setter
    @Getter
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created_at;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updated_at;

    public String getCompanyName() {
        if (company != null) return company.getName();
        return null;
    }

    public void setStationId(Integer id) {
        this.id = id;
    }
}
