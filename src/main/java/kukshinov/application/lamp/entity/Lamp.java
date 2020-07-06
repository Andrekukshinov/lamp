package kukshinov.application.lamp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "lamps")
public class Lamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean state;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
//    @MapsId
//    @JsonIgnore
    @JsonBackReference
    private Room room;

    public Lamp(long id, boolean state, Room room) {
        this.id = id;
        this.state = state;
        this.room = room;
    }

    public Lamp() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Lamp{" + "id=" + id + ", state=" + state + '}';
    }
}
