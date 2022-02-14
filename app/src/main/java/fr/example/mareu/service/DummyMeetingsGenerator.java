package fr.example.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;

public abstract class DummyMeetingsGenerator {

    public static List<Meeting> MEETING_LAMZONE = Arrays.asList(

            new Meeting("Sujet Mario", DummyWorkMatesGenerator.generateWorkmate(), Room.MARIO, getDate(10,30)),
            new Meeting("sujet Luigi", DummyWorkMatesGenerator.generateWorkmate(), Room.LUIGI, getDate(11,30)),
            new Meeting("sujet Peach", DummyWorkMatesGenerator.generateWorkmate(), Room.PEACH, getDate(12,30)),
            new Meeting("sujet Wario", DummyWorkMatesGenerator.generateWorkmate(), Room.WARIO, getDate(13,30))
    );

    public static List<Meeting> generateMeeting()
    {
        return new ArrayList<>(MEETING_LAMZONE);
    }

    public static Date getDate ( int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime(); }
}
