package com.example.StoringHostelData.entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "guest")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "second_name")
    private String secondName;

    private String name;
    private String surname;
    private Boolean gender;

    @Column(name = "date_added")
    private LocalDate dateAdded;

    @Column(name = "date_of_change")
    private LocalDate dateOfChange;

    public Guest(Room room, String secondName, String name, String surname, Boolean gender, LocalDate dateAdded, LocalDate dateOfChange) {
        this.room = room;
        this.secondName = secondName;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateAdded = dateAdded;
        this.dateOfChange = dateOfChange;
    }

    public Guest() {

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

    public void  setRoom(Room roomId) {
        this.room = room;
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
