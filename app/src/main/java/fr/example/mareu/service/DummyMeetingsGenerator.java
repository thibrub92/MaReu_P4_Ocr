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
            new Meeting(1,"sujet Luigi", DummyWorkMatesGenerator.generateWorkmate(), Room.LUIGI, Calendar.getInstance().getTime()),
            new Meeting(2,"sujet Peach", DummyWorkMatesGenerator.generateWorkmate(), Room.PEACH, Calendar.getInstance().getTime()),
            new Meeting(3,"sujet Wario", DummyWorkMatesGenerator.generateWorkmate(), Room.WARIO, Calendar.getInstance().getTime()),
            new Meeting(4,"sujet Daisy", DummyWorkMatesGenerator.generateWorkmate(), Room.DAISY, Calendar.getInstance().getTime()),
            new Meeting(5,"sujet Bowser", DummyWorkMatesGenerator.generateWorkmate(), Room.BOWSER, Calendar.getInstance().getTime()),
            new Meeting(6,"sujet Yoshi", DummyWorkMatesGenerator.generateWorkmate(), Room.YOSHI, Calendar.getInstance().getTime()),
            new Meeting(7,"sujet Goomba", DummyWorkMatesGenerator.generateWorkmate(), Room.GOOMBA, Calendar.getInstance().getTime())
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
