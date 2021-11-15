package fr.example.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.example.mareu.R;
import fr.example.mareu.model.Room;
import fr.example.mareu.model.Workmate;

public abstract class DummyRoomsGenerator {

    public static List<Room> ROOMS_LAMZONE = Arrays.asList(

                new Room("Mario", R.drawable.ic_room_mario,R.color.red ),
                new Room("Luigi", R.drawable.ic_room_luigi,R.color.darkGreen),
                new Room("Peach", R.drawable.ic_room_peach,R.color.pink ),
                new Room("Daisy", R.drawable.ic_room_daisy,R.color.yellow ),
                new Room("Wario", R.drawable.ic_room_wario,R.color.purple ),
                new Room("Bowser", R.drawable.ic_room_bowser,R.color.orange ),
                new Room("Yoshi", R.drawable.ic_room_yoshi,R.color.green ),
                new Room("Goomba", R.drawable.ic_room_goomba,R.color.browne )
        );
        static List<Room> generateRooms() {
            return new ArrayList<>(ROOMS_LAMZONE);
        }
    }



