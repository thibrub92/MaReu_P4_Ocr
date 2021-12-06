package fr.example.mareu;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;
import fr.example.mareu.databinding.ActivityMainBinding;
import fr.example.mareu.event.DeleteMeetingEvent;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.service.ApiServiceMeetings;
import fr.example.mareu.DI.DI;



public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ApiServiceMeetings apiServiceMeetings;
    private MeetingListAdapter adapter;
    private List<Meeting> meetingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiServiceMeetings = DI.getApiServiceMeetings();
        meetingList= apiServiceMeetings.getMeetingList();
        adapter= new MeetingListAdapter(meetingList,this);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        binding.recyclerMeeting.setLayoutManager(linearLayoutManager);
        binding.recyclerMeeting.setAdapter(adapter);

        binding.addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateMeetingActivity.class));
            }
        });
    }
    private void initList() {
        meetingList = apiServiceMeetings.getMeetingList();
        binding.recyclerMeeting.setAdapter(new MeetingListAdapter(meetingList,this));
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



