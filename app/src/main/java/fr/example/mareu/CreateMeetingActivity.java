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
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;
import java.util.Locale;
import fr.example.mareu.DI.DI;
import fr.example.mareu.databinding.ActivityCreateMeetingBinding;

import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;
import fr.example.mareu.model.Workmate;
import fr.example.mareu.service.ApiServiceMeetings;
import fr.example.mareu.service.ApiServiceWorkMate;
import fr.example.mareu.utils.DateTimeConverter;


public class CreateMeetingActivity extends AppCompatActivity {

    private ActivityCreateMeetingBinding binding;
    private ApiServiceMeetings apiServiceMeetings;
    private ApiServiceWorkMate apiServiceWorkMate;
    private List<Workmate> workMateList = new ArrayList<>();
    private List<Workmate> presentWorkmateList = new ArrayList<>();
    private Calendar selectedCalendar= Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiServiceMeetings = DI.getApiServiceMeetings();
        apiServiceWorkMate = DI.getApiServiceWorkMate();

        binding.inputDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();

                int mYear= mcurrentDate.get(Calendar.YEAR);
                int mMonth= mcurrentDate.get(Calendar.MONTH);
                int mDay= mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(CreateMeetingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        selectedCalendar.set(Calendar.YEAR,year);
                        selectedCalendar.set(Calendar.MONTH,month);
                        selectedCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        binding.inputDateButton.setText(DateTimeConverter.getFormattedDate(year, month, dayOfMonth));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.show();
            }
        });

        binding.inputTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                            selectedCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                            selectedCalendar.set(Calendar.MINUTE,minute);
                            binding.inputTimeButton.setText(DateTimeConverter.getFormattedTime(hourOfDay, minute));
                        }
                    },9,0,true);
                    mTimePicker.show();
                }
        });

        workMateList = apiServiceWorkMate.getWorkmateList();

        binding.buttonSelectedParticipant.setOnClickListener(new View.OnClickListener() {
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

    private void createMeeting() {
        if (canCreateMeeting()) {
            Room selectedRoom = (Room) binding.spinnerRoom.getSelectedItem();

            Meeting meeting = new Meeting(
                    binding.inputSubject.getText().toString(),
                    presentWorkmateList,
                    selectedRoom,
                    selectedCalendar.getTime());

            apiServiceMeetings.createMeeting(meeting);
            onBackPressed();
        }
    }

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
        if(presentWorkmateList.isEmpty()){
            Toast.makeText(this,"*Choisir participants",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(binding.inputDateButton.getText().toString().isEmpty()){
            Toast.makeText(this,"*date obligatoire",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.inputTimeButton.getText().toString().isEmpty()){
            Toast.makeText(this,"*heure obligatoire",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(selectedCalendar.getTimeInMillis()< Calendar.getInstance().getTimeInMillis()){
            Toast.makeText(this,"*Choisir une date non intérieur",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isRoomAvailable()){
            Toast.makeText(this,"*Salle non disponible",Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }

    private boolean isRoomAvailable(){
        return apiServiceMeetings.isMeetingCanBeCreated(
                selectedCalendar.getTime(),
                (Room) binding.spinnerRoom.getSelectedItem());
    }

    private void showParticipantsDialog(List<Workmate> workMateList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choisir participants");

        String[] emailList = new String [workMateList.size()] ;
        boolean[] booleanList = new boolean [workMateList.size()] ;

        for (int i =0 ; i < workMateList.size(); i++){
            Workmate workmate = workMateList.get(i);
            emailList[i] = workmate.getEmail(); // on recup l'email pour chaque w de la liste
            boolean isAlreadyInvited = presentWorkmateList.contains(workmate);
            booleanList[i] = isAlreadyInvited ;
        }

        builder.setMultiChoiceItems(emailList,booleanList, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int index, boolean isChecked) {
                Workmate workmate = workMateList.get(index);

                if (isChecked) {
                    presentWorkmateList.add(workmate);
                } else {
                    presentWorkmateList.remove(workmate); }
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder builder = new StringBuilder();

                for(Workmate w : presentWorkmateList){
                    builder.append(w.getFirstName()).append(" ").append(w.getName()).append(" - ");
                }
                binding.txtParticipant.setText(builder.toString());
            }
        });

        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
