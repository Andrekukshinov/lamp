package kukshinov.application.lamp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country_name")
    private String countryName;
    @OneToMany(mappedBy = "country", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    @JsonIgnore
    private List<Room> rooms;

    public Country(Long id, String countryName, List<Room> rooms) {
        this.id = id;
        this.countryName = countryName;
        this.rooms = rooms;
    }

    public Country() {
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}

