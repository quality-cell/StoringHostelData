package com.example.StoringHostelData.repository;

import com.example.StoringHostelData.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
    List<Guest> findByRoomId(Integer roomId);
}
