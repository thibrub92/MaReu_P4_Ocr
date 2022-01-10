package fr.example.mareu;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import fr.example.mareu.databinding.ActivityMainBinding;
import fr.example.mareu.event.DeleteMeetingEvent;
import fr.example.mareu.model.DateHour;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;
import fr.example.mareu.model.Workmate;
import fr.example.mareu.service.ApiServiceMeetings;
import fr.example.mareu.DI.DI;



public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ApiServiceMeetings apiServiceMeetings;
    private MeetingListAdapter adapter;
    private List<Meeting> meetingList;

    private TextInputEditText beginDate;
    private TextInputEditText endDate;
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiServiceMeetings = DI.getApiServiceMeetings();
        meetingList = apiServiceMeetings.getMeetingList();
        adapter = new MeetingListAdapter(meetingList, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerMeeting.setLayoutManager(linearLayoutManager);
        binding.recyclerMeeting.setAdapter(adapter);

        binding.addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateMeetingActivity.class));
            }
        });
    }

    private void initList(List<Meeting> meetingList) {
        binding.recyclerMeeting.setAdapter(new MeetingListAdapter(meetingList, this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        initList(apiServiceMeetings.getMeetingList());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        apiServiceMeetings.deleteMeetingItem(event.meeting);
        initList(apiServiceMeetings.getMeetingList());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.trier_date:
                // Toast.makeText(this, "Trier par dates", Toast.LENGTH_SHORT).show();
                showDatesDialog();
                return true;

            case R.id.trier_rooms:
                showRoomsDialog();
                return true;

            case R.id.without_filter:
                initList(apiServiceMeetings.getMeetingList());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showRoomsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Séléctionner Salles");

        List<Room> selectedRooms = new ArrayList<>();
        String[] roomList = new String[Room.values().length];
        boolean[] booleanList = new boolean[Room.values().length];
        int i = 0;


        for (Room r : Room.values()) {
            roomList[r.ordinal()] = getString(r.getNameRes());
            i++;
        }

        builder.setMultiChoiceItems(roomList, booleanList, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int index, boolean isChecked) {
                Room room = Room.values()[index];

                if (isChecked) {
                    selectedRooms.add(room);
                } else {
                    selectedRooms.remove(room);
                }
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Meeting> filteredMeetings = apiServiceMeetings.filterRooms(selectedRooms);
                initList(filteredMeetings);
            }
        });

        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDatesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        Calendar filteredCalandar =  Calendar.getInstance();

        String[] dateList = new String[Calendar.getInstance().getTime()];
        builder.setView(inflater.inflate(R.layout.filter_date_hour, null));
        builder.setTitle("Choisir une période");

        builder.setPositiveButton("valider", (DialogInterface dialog, int which) -> {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Calendar calendar = apiServiceMeetings.filterDateHour(selectedCalandar);


                if (checkBox.isChecked() && beginDate.getText().length() > 0 && endDate.getText().length() > 0) {
                //.//()beginDate.getText().toString(),
                endDate.getText().toString();
                initList();
            }

        });

        builder.setNegativeButton("Annuler", (DialogInterface dialog, int which) -> {


        });
        return null;
        }
    }






