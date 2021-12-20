package fr.example.mareu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import java.sql.Time;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import fr.example.mareu.DI.DI;
import fr.example.mareu.databinding.ActivityCreateMeetingBinding;
import fr.example.mareu.model.DateHour;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;
import fr.example.mareu.model.Workmate;
import fr.example.mareu.service.ApiServiceMeetings;
import fr.example.mareu.service.ApiServiceWorkMate;


public class CreateMeetingActivity extends AppCompatActivity {

    private ActivityCreateMeetingBinding binding;
    private ApiServiceMeetings apiServiceMeetings;
    private ApiServiceWorkMate apiServiceWorkMate;
    private List<Workmate> workMateList = new ArrayList<>();
    private TimePicker timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiServiceMeetings = DI.getApiServiceMeetings();
        apiServiceWorkMate = DI.getApiServiceWorkMate();

        binding.inputDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();

                int mYear= mcurrentDate.get(Calendar.YEAR);
                int mMonth= mcurrentDate.get(Calendar.MONTH);
                int mDay= mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(CreateMeetingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        binding.inputDate.setText(dayOfMonth + "/ "+"");
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.show();
            }
        });
        binding.inputTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hour, minute ;
                public void  onTimeSet (TimePicker timePicker,int selectedHour,int selectedMinute){
                    hour = selectedHour;
                    minute = selectedMinute;
                           binding.inputTime.setOnClickListener(this);
                           String.format(Locale.getDefault(),
                                   "format",
                                   hour, minute);  // %02d:%02d
                }
            }
        });

        workMateList = apiServiceWorkMate.getWorkmateList();

        binding.buttonSelectedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showParticipantsDialog(workMateList);
            }
        });

        binding.spinnerRoom.setAdapter(new ArrayAdapter<Room>(this, android.R.layout.simple_list_item_1 , Room.values()));

        binding.okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMeeting();
            }
        });
    }
    public void popTimePicker(){
    android.app.TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        }};
    }

    private void createMeeting() {
        if (canCreateMeeting()) {
            Room selectedRoom = (Room) binding.spinnerRoom.getSelectedItem();

            Meeting meeting = new Meeting(
                    binding.inputSubject.getText().toString(),
                    workMateList,
                    selectedRoom,
                    Calendar.getInstance().getTime()
            );
            apiServiceMeetings.createMeeting(meeting);
            onBackPressed();
        }
    }
    int style =  AlertDialog.THEME_HOLO_DARK;

    private boolean canCreateMeeting(){
        //  Vérification que tous les champs sont remplis

        if(binding.inputSubject.getText().toString().isEmpty()){
            Toast.makeText(this,"*Sujet Obligatoire",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(binding.spinnerRoom.getAdapter().isEmpty()){
            Toast.makeText(this,"*Choix salle obligatoire",Toast.LENGTH_SHORT).show();
            return false;
        }
          if(binding.buttonSelectedButton.setOnClickListener(view -> workMateList).isEmpty()){
           Toast.makeText(this,"*Nombres de personnes obligatoire",Toast.LENGTH_SHORT).show();
           return false;
        }

        if(binding.inputDate.getText().toString().isEmpty()){
            Toast.makeText(this,"*date obligatoire",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.inputTime.getText().toString().isEmpty()){
            Toast.makeText(this,"*heure obligatoire",Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true; }

    private void showParticipantsDialog(List<Workmate> workMateList) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choisir participants");

        boolean[] checkedItems = new boolean[workMateList.size()] ;
        for (int i =0 ; i < workMateList.size(); i++){
            checkedItems[i] = false;

        }
        builder.setSingleChoiceItems(workMateList.toArray(new String[0]), checkedItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
