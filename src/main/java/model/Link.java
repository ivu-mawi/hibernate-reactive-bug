package model;

import java.util.List;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Link extends PanacheEntityBase {

    @EmbeddedId
    public LinkKey key;

    @OneToMany(mappedBy = "link", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<GPSPoint> gpsPoints;

}
