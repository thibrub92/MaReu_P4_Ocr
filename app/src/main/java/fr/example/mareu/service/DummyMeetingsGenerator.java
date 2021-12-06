package fr.example.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;
import fr.example.mareu.model.Workmate;

public abstract class DummyMeetingsGenerator {

    public static List<Meeting> MEETING_LAMZONE = Arrays.asList(


            new Meeting(0,"sujet Mario", DummyWorkMatesGenerator.generateWorkmate(), Room.MARIO, getDate(10,30  )),
            new Meeting(1,"sujet Luigi", DummyWorkMatesGenerator.generateWorkmate(), Room.LUIGI, getDate(11,30)),
            new Meeting(2,"sujet Peach", DummyWorkMatesGenerator.generateWorkmate(), Room.PEACH,getDate(12,30)),
            new Meeting(3,"sujet Wario", DummyWorkMatesGenerator.generateWorkmate(), Room.WARIO, getDate(13,30)),
            new Meeting(4,"sujet Daisy", DummyWorkMatesGenerator.generateWorkmate(), Room.DAISY, getDate(14,30)),
            new Meeting(5,"sujet Bowser", DummyWorkMatesGenerator.generateWorkmate(), Room.BOWSER,getDate(15,30)),
            new Meeting(6,"sujet Yoshi", DummyWorkMatesGenerator.generateWorkmate(), Room.YOSHI,getDate(16,30)),
            new Meeting(7,"sujet Goomba", DummyWorkMatesGenerator.generateWorkmate(), Room.GOOMBA, getDate(17,30))
    );


    static List<Meeting> generateMeeting()
    {
        return new ArrayList<>(MEETING_LAMZONE);
    }

    static Date getDate ( int hour, int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }


}
