package fr.example.mareu.service;

import java.util.List;
import java.util.Map;

import fr.example.mareu.model.Room;

public interface ApiServiceRoom {

    List<Room> getRoomList();

    Map<String, Room> createPlaceRoom();

    void deleteRoomItem (Room room);
}
