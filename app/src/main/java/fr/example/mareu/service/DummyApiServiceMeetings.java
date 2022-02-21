package fr.example.mareu.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;

public class DummyApiServiceMeetings implements ApiServiceMeetings {

    private List<Meeting> meetings = DummyMeetingsGenerator.generateMeeting();

    @Override
    public List<Meeting> getMeetingList() {  //OK
        return meetings;
    }

    @Override
    public void createMeeting(Meeting meeting) { //OK
        meetings.add(meeting);
    }

    @Override
    public void deleteMeetingItem(Meeting meeting) {//OK
        meetings.remove(meeting);
    }

    @Override
    public boolean isMeetingCanBeCreated(Date date, Room room) {
        for (Meeting m : meetings) {
            Date meetingDate = m.getDate();
            long timeDifference = meetingDate.getTime() - date.getTime();
            long differenceMinute = timeDifference / (1000 * 60);

            if (differenceMinute > -60 && differenceMinute <= 59) {    // if same slots hour
                if (m.getRoom() == room) {                            // if same room
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<Meeting> filterDateHour(Date beginDate, Date endDate) {
        List<Meeting> filteredMeetings = new ArrayList<>();

        Calendar calendarBegin = Calendar.getInstance();
        calendarBegin.setTime(beginDate);
        calendarBegin.set(Calendar.HOUR_OF_DAY, 0);
        calendarBegin.set(Calendar.MINUTE, 0);
        calendarBegin.set(Calendar.SECOND, 0);

        Calendar calendarEnd = Calendar.getInstance();
        calendarBegin.setTime(endDate);
        calendarBegin.set(Calendar.HOUR_OF_DAY, 23);
        calendarBegin.set(Calendar.MINUTE, 59);
        calendarBegin.set(Calendar.SECOND, 59);

        for (Meeting m : meetings) {
            // check if date "m" is between the 2 start/end dates
            if (m.getDate().after(calendarBegin.getTime()) && m.getDate().before(calendarEnd.getTime())) {
                filteredMeetings.add(m);
            }
        }
        return filteredMeetings;
    }

    @Override
    public List<Meeting> filterRooms(List<Room> rooms) {
        List<Meeting> filteredMeetings = new ArrayList<>();

        for (Meeting m : meetings) {
            if (rooms.contains(m.getRoom())) {
                filteredMeetings.add(m);
            }
        }
        return filteredMeetings;
    }
}

