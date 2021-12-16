package fr.example.mareu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.example.mareu.DI.DI;
import fr.example.mareu.databinding.ActivityCreateMeetingBinding;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;
import fr.example.mareu.model.Workmate;
import fr.example.mareu.service.ApiServiceMeetings;


public class CreateMeetingActivity extends AppCompatActivity {

    private ActivityCreateMeetingBinding binding;
    private ApiServiceMeetings apiServiceMeetings;
    private List<Workmate> workMateList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiServiceMeetings = DI.getApiServiceMeetings();

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

                    }
                       }, mYear, mMonth, mDay);
                mDatePicker.show();
                  }
        });

        binding.multiSpinnerWorkmate.setAdapter(new ArrayAdapter<Workmate>(this, android.R.layout.simple_list_item_1, workMateList));

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
            Workmate selectedWorkmate= (Workmate) binding.multiSpinnerWorkmate.getSelectedItem();
            workMateList.add(selectedWorkmate);

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
        if(binding.multiSpinnerWorkmate.getAdapter().isEmpty()){
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
}
