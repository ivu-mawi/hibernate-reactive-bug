package model;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class NetPoint extends PanacheEntityBase {

    @EmbeddedId
    public NetPointKey key;

    @Column(nullable = false)
    public String operatingDepartmentId;

    @Column(nullable = false)
    public String operatingDepartmentShortName;

    @Column(nullable = false)
    public Integer gpsLatitude;

    @Column(nullable = false)
    public Integer gpsLongitude;
}
