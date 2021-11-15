package fr.example.mareu.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.example.mareu.model.Room;

public class DummyApiServiceRooms implements Serializable {

    private List<Room> rooms = DummyRoomsGenerator.generateRooms();

    private Map<String, Room> mListRoom;

    public List<Room> getRoomList() {
        return null;
    }


    public Map<String,Room> createPlaceRoom() {
        mListRoom = new HashMap<String, Room>();
        for (int i = 0; i < rooms.size(); i++) {
            mListRoom.put(rooms.get(i).getDeclaringClass().getName(), rooms.get(i));
        }
        return mListRoom;
    }

}
