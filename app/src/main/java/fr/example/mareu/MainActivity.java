package fr.example.mareu;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;


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
    private RecyclerView recyclerView;
    private MeetingListAdapter adapter;
    private List<Meeting> meetingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiServiceMeetings = DI.getApiServiceMeetings();

        meetingList= apiServiceMeetings.getMeetingList();
        recyclerView = findViewById(R.id.recycler_meeting);
        adapter= new MeetingListAdapter(meetingList);


        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}



