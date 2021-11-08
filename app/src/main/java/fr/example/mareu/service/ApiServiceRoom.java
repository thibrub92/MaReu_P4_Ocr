package fr.example.mareu.service;

import java.util.List;

import fr.example.mareu.model.Room;

public interface ApiServiceRoom {

    List<Room> getRoomList();

    void createRoom (Room room);

    void deleteRoomItem (Room room);
}
