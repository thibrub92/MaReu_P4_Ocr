package fr.example.mareu.DI;

import fr.example.mareu.MainActivity;
import fr.example.mareu.service.ApiServiceMeetings;
import fr.example.mareu.service.ApiServiceRoom;
import fr.example.mareu.service.ApiServiceWorkMate;
import fr.example.mareu.service.DummyApiServiceMeetings;
import fr.example.mareu.service.DummyApiServiceWorkMates;

public class DI {

    private final static ApiServiceMeetings apiServiceMeetings = new DummyApiServiceMeetings();
    private final static ApiServiceWorkMate apiServiceWorkmate = new DummyApiServiceWorkMates();
    private final static ApiServiceRoom apiServiceRoom = new MainActivity.DummyApiServiceRoom();


    public static ApiServiceMeetings getApiServiceMeetings(){
        return apiServiceMeetings;
    }

    public static ApiServiceWorkMate getApiServiceWorkMate(){
        return apiServiceWorkmate;
    }

    public static ApiServiceRoom getApiServiceRoom(){return  apiServiceRoom;}
}
