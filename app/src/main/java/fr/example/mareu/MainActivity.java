package fr.example.mareu;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import fr.example.mareu.DI.DI;
import fr.example.mareu.databinding.ActivityMainBinding;
import fr.example.mareu.event.DeleteMeetingEvent;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;
import fr.example.mareu.service.ApiServiceMeetings;

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

        for (Room r : Room.values()) {
            roomList[r.ordinal()] = getString(r.getNameRes());
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

        builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Meeting> filteredMeetings = apiServiceMeetings.filterRooms(selectedRooms);
                initList(filteredMeetings);
            }
        });

        builder.setNegativeButton("Annuler", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDatesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.filter_date_hour, null);
        builder.setView(view);
        builder.setTitle("Choisir une période");
        Button beginEditDate = view.findViewById(R.id.input_filter_date_start);
        Button endEditDate = view.findViewById(R.id.input_filter_date_end);

        beginEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();

                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        month = month + 1;
                        String date = String.format(Locale.getDefault(),
                                "%02d/%02d/%d",
                                dayOfMonth, month, year);
                        beginEditDate.setText(date);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.show();
            }
        });

        endEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();

                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        month = month + 1;
                        String date = String.format(Locale.getDefault(),
                                "%02d/%02d/%d",
                                dayOfMonth, month, year);
                        endEditDate.setText(date);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.show();
            }
        });

        builder.setPositiveButton("valider", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (beginEditDate.getText().toString().isEmpty()) {
                    Toast.makeText(view.getContext(), "*Merci de choisir une date de début", Toast.LENGTH_SHORT).show();
                } else if (endEditDate.getText().toString().isEmpty()) {
                    Toast.makeText(view.getContext(), "*Merci de choisir une date de fin", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date beginDate = dateFormat.parse(beginEditDate.getText().toString());
                        Date endDate = dateFormat.parse(endEditDate.getText().toString());

                        if (endDate.after(beginDate)) {
                            List<Meeting> filteredMeetings = apiServiceMeetings.filterDateHour(beginDate, endDate);
                            initList(filteredMeetings);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(view.getContext(), "*Merci de choisir une date de debut avant la date de fin", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(view.getContext(), "*Format date non valide", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}








