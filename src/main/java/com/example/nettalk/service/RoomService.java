package com.example.nettalk.service;

import com.example.nettalk.entity.room.Room;
import com.example.nettalk.entity.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public List<Room> list() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoom(Long id) {
        return roomRepository.findById(id);
    }

    public void createRoom(Room room) {
        roomRepository.save(room);
    }
}
