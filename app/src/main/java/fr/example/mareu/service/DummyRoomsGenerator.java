package fr.example.mareu.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.example.mareu.R;
import fr.example.mareu.model.Room;

public class DummyRoomsGenerator {


public static List<Room> ROOM_LAMZONE = Arrays.asList(

        new Room (R.string.Mario, R.drawable.ic_room_mario,R.color.red),
        new Room (R.string.Luigi, R.drawable.ic_room_luigi, R.color.darkGreen),
        new Room (R.string.Peach, R.drawable.ic_room_peach, R.color.pink),
        new Room (R.string.Daisy, R.drawable.ic_room_daisy, R.color.yellow),
        new Room (R.string.Wario, R.drawable.ic_room_wario, R.color.purple),
        new Room (R.string.Yoshi, R.drawable.ic_room_yoshi, R.color.green),
        new Room (R.string.Goomba, R.drawable.ic_room_goomba, R.color.browne),
        new Room (R.string.Bowser, R.drawable.ic_room_bowser, R.color.orange)
);

    static List<Room> generateRooms() {
        return new ArrayList<>(ROOM_LAMZONE);
    }
}


