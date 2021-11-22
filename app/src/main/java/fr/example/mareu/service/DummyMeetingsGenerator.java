package fr.example.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;
import fr.example.mareu.model.Workmate;

public abstract class DummyMeetingsGenerator {

    public static List<Meeting> MEETING_LAMZONE = Arrays.asList(

       // creation des differentes reunions
            new Meeting(0,"sujet", DummyWorkMatesGenerator.generateWorkmate(), Room.MARIO, Calendar.getInstance().getTime()),
                    new Meeting(0,"sujet", DummyWorkMatesGenerator.generateWorkmate(), Room.MARIO, Calendar.getInstance().getTime()),
            new Meeting(0,"sujet", DummyWorkMatesGenerator.generateWorkmate(), Room.MARIO, Calendar.getInstance().getTime())

            );


    static List<Meeting> generateMeeting()
    {
        return new ArrayList<>(MEETING_LAMZONE);
    }


}
