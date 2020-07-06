package kukshinov.application.lamp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;


@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "room_name")
    private String roomName;

    @ManyToOne()
    @JoinColumn(name ="country_id")
    private Country country;

    @OneToOne(mappedBy = "room",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Lamp lamp;



    public Room(long id, String roomName, Lamp lamp, Country country) {
        this.id = id;
        this.roomName = roomName;
        this.lamp = lamp;
        this.country = country;
    }

    public Room() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Lamp getLamp() {
        return lamp;
    }

    public void setLamp(Lamp lamp) {
        this.lamp = lamp;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", roomName='" + roomName + '\'' + ", country=" + country + ", lamp=" + lamp + '}';
    }
}
