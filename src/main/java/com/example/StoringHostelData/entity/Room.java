package com.example.StoringHostelData.entity;

import com.example.StoringHostelData.Enum.ComfortType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "room")
    private List<Guest> guest;

    @Column(columnDefinition = "integer CHECK (floor >= 1 AND floor <= 10)")
    private Integer floor;

    @Column(name = "room_number", columnDefinition = "integer CHECK (roomNumber >= 1 AND roomNumber <= 100)")
    private Integer roomNumber;

    @Column(name = "room_type")
    private Boolean roomType;

    @Enumerated(EnumType.STRING)
    @Column(name = "comfort_type")
    private ComfortType comfortType;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @Column(name = "date_added")
    private LocalDate dateAdded;

    @Column(name = "date_of_change")
    private LocalDate dateOfChange;

    public Room(Integer floor, Integer roomNumber, Boolean roomType, ComfortType comfortType, Integer numberOfSeats, LocalDate dateAdded, LocalDate dateOfChange) {
        if (floor < 1 || floor > 10) {
            throw new IllegalArgumentException("Floor should be between 1 and 10");
        }

        if (roomNumber < 1 || roomNumber > 100) {
            throw new IllegalArgumentException("Room number should be between 1 and 100");
        }

        this.floor = floor;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.comfortType = comfortType;
        this.numberOfSeats = numberOfSeats;
        this.dateAdded = dateAdded;
        this.dateOfChange = dateOfChange;
    }

    public Room() {

    }

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
    }

    public List<Guest> getGuest() {
        return guest;
    }

    public void setGuest(List<Guest> guest) {
        this.guest = guest;
    }

    public Integer getFloor() {
        return floor;
    }

    public void  setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void  setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Boolean getRoomType() {
        return roomType;
    }

    public void setRoomType(Boolean roomType) {
        this.roomType = roomType;
    }

    public ComfortType getComfortType() {
        return comfortType;
    }

    public void setComfortType(ComfortType comfortType) {
        this.comfortType = comfortType;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDate getDateOfChange() {
        return dateOfChange;
    }

    public void setDateOfChange(LocalDate dateOfChange) {
        this.dateOfChange = dateOfChange;
    }

}
