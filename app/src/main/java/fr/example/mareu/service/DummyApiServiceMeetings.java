package fr.example.mareu.service;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import fr.example.mareu.R;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;

public class DummyApiServiceMeetings implements ApiServiceMeetings {

    private List<Meeting> meetings = DummyMeetingsGenerator.generateMeeting();

    @Override
    public List<Meeting> getMeetingList() {
        return meetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeetingItem(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public boolean isMeetingCanBeCreated(Date date , Room room) {
        for (Meeting m : meetings) {
            Date meetingDate = m.getDate();
            long timeDifference = meetingDate.getTime() - date.getTime();
            long differenceMinute = timeDifference / (1000 * 60);

            if (differenceMinute > -59 && differenceMinute <= 59) {    // si même créneau horaire
                if (m.getRoom() == room) {                            // Si même room
                    return false;
                }
            }
        } return true;
    }

    @Override
    public List<Meeting> filterDateHour(List<Meeting> meetings, List<Room> rooms, Date date) {
        return filterDateHour (filterDateHour(meetings, rooms), date);
    }

    @Override
    public List<Room> filterRooms(List<Meeting> meetings, List<Room> rooms, Date date) {
        return filterRooms(List<Meeting> meetings,List<Room>rooms,date);

        public void initializeListCheckBox() {

            filterRooms(filterRooms()) = Arrays.asList(
                    Objects.requireNonNull(filterRooms(Room)).findViewById(R.id.checkbox_filter_mario),
                    Objects.requireNonNull(filterRooms()).findViewById(R.id.checkbox_filter_luigi),
                    Objects.requireNonNull(filterRooms()).findViewById(R.id.checkbox_filter_peach),
                    Objects.requireNonNull(filterRooms()).findViewById(R.id.checkbox_filter_daisy),
                    Objects.requireNonNull(filterRooms()).findViewById(R.id.checkbox_filter_wario),
                    Objects.requireNonNull(filterRooms()).findViewById(R.id.checkbox_filter_yoshi),
                    Objects.requireNonNull(filterRooms()).findViewById(R.id.checkbox_filter_bowser),
                    Objects.requireNonNull(filterRooms()).findViewById(R.id.checkbox_filter_goomba)
            );

            // Initialiser liste CheckBox
            for(int i = 0; i < filterRooms(List<Room>).size(); i++){
                filterRooms().get(i).setChecked(roomFiltersSelected[i]);
            }
        }
    }
    @Override
    public List<Meeting> withoutFilter(List<Meeting> meetings) {
        return withoutFilter(null);
    }
}
