package fr.example.mareu;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import fr.example.mareu.event.DeleteMeetingEvent;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.service.ApiServiceMeetings;
import fr.example.mareu.DI.DI;



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
        adapter= new MeetingListAdapter(meetingList,this);



        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initList() {
        meetingList = apiServiceMeetings.getMeetingList();
        recyclerView.setAdapter(new MeetingListAdapter(meetingList,this));
    }

    @Override
    public void onStart () {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop () {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteMeeting (DeleteMeetingEvent event){
        apiServiceMeetings.deleteMeetingItem(event.meeting);
        initList();
    }
}



