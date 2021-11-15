package fr.example.mareu;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


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




    }
}



