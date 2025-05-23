package model;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class GPSPoint extends PanacheEntityBase {

    @EmbeddedId
    public GPSPointKey key;

    @ManyToOne
    @MapsId("link")
    public Link link;

    @Column(nullable = false)
    public Integer latitude;

    @Column(nullable = false)
    public Integer longitude;

    /**
     * distance to the start point of the link in meters
     */
    @Column(nullable = false)
    public Integer distance;
}
