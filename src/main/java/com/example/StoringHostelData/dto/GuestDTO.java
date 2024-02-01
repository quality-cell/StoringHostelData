package com.example.StoringHostelData.dto;

import com.example.StoringHostelData.entity.Guest;
import com.example.StoringHostelData.entity.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class GuestDTO {
    private Integer id;
    private Integer roomId;
    private String secondName;
    private String name;
    private String surname;
    private Boolean gender;
    private LocalDate dateAdded;
    private LocalDate dateOfChange;

    @JsonIgnore
    private Room room;

    public GuestDTO(Guest guest) {
        this.id = guest.getId();
        this.room = guest.getRoom();
        this.roomId = guest.getRoom().getId();
        this.secondName = guest.getSecondName();
        this.name = guest.getName();
        this.surname = guest.getSurname();
        this.gender = guest.getGender();
        this.dateAdded = guest.getDateAdded();
        this.dateOfChange = guest.getDateOfChange();
    }

    public GuestDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void  setRoom(Room room) {
        this.room = room;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void  setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
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
