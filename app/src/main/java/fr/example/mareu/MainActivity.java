package fr.example.mareu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Map;

import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;
import fr.example.mareu.service.ApiServiceMeetings;
import fr.example.mareu.DI.DI;
import fr.example.mareu.service.ApiServiceRoom;

public class MainActivity extends AppCompatActivity {

    private ApiServiceMeetings apiServiceMeetings;
    private FloatingActionButton addMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiServiceMeetings = DI.getApiServiceMeetings();

        // recuperer  la liste de meetings


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.meeting_itemview);
            apiServiceMeetings = DI.getApiServiceMeetings();
            Meeting meeting = (Meeting) getIntent().getSerializableExtra("meeting");

            // une reunion
            ImageView imageViewId = findViewById(R.id.imageViewId);
            TextView titleReunion = findViewById(R.id.title_reunion);
            TextView workmateMail = findViewById(R.id.itemView_mail);
            ImageView deleteButtonMeeting = findViewById(R.id.delete_button_itemView);

            // information add reunion
            TextInputEditText subject = findViewById(R.id.input_subject);
            TextInputEditText participant = findViewById(R.id.input_people);
            TextInputEditText room = findViewById(R.id.input_room);
            TextInputEditText time = findViewById(R.id.input_time);
            FloatingActionButton validerButton = findViewById(R.id.ok_button);

            //creer un adapteur  avec cette liste pour l'afficher avec un recyclerview
            subject.setText(meeting.getSubject());
            participant.setText((CharSequence) meeting.getParticipants());
            room.setText(meeting.getRoom());
            time.setText((CharSequence) meeting.getDate());

        }
    }
    public static class DummyApiServiceRoom implements ApiServiceRoom {

        @Override
        public List<Room> getRoomList() {
            return null;
        }

        @Override
        public Map<String, Room> createPlaceRoom() {
            return null; }

        @Override
        public void deleteRoomItem(Room room) {

        }
    }
}

