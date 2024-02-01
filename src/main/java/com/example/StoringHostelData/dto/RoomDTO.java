package com.example.StoringHostelData.dto;

import com.example.StoringHostelData.Enum.ComfortType;
import com.example.StoringHostelData.entity.Room;

import java.time.LocalDate;

public class RoomDTO {
    private Integer id;
    private Integer floor;
    private Integer roomNumber;
    private Boolean roomType;
    private ComfortType comfortType;
    private Integer numberOfSeats;
    private LocalDate dateAdded;
    private LocalDate dateOfChange;

    public RoomDTO(Room room) {
        this.id = room.getId();
        this.floor = room.getFloor();
        this.roomNumber = room.getRoomNumber();
        this.roomType = room.getRoomType();
        this.comfortType = room.getComfortType();
        this.numberOfSeats = room.getNumberOfSeats();
        this.dateAdded = room.getDateAdded();
        this.dateOfChange = room.getDateOfChange();
    }

    public RoomDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
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
