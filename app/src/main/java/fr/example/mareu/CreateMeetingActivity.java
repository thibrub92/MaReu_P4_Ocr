package fr.example.mareu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobParameters;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;

import javax.security.auth.Subject;

import fr.example.mareu.DI.DI;
import fr.example.mareu.databinding.ActivityCreateMeetingBinding;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;
import fr.example.mareu.service.ApiServiceMeetings;

public class CreateMeetingActivity extends AppCompatActivity {

    private ActivityCreateMeetingBinding binding;
    private JobParameters jobParameters;
    private ApiServiceMeetings apiServiceMeetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiServiceMeetings = DI.getApiServiceMeetings();

        binding.spinnerRoom.setAdapter(new ArrayAdapter<Room>(this, android.R.layout.simple_list_item_2 , Room.values()));

        binding.okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMeeting();
            }
        });
    }
    private void createMeeting(){
        if(canCreateMeeting()){
            Room selectedRoom = (Room) binding.spinnerRoom.getSelectedItem();

            Meeting meeting= new Meeting(
                    binding.inputSubject.getText().toString(),
                    new ArrayList<>(),
                    selectedRoom,
                    Calendar.getInstance().getTime()
            );
            apiServiceMeetings.createMeeting(meeting);
            onBackPressed();
        }
    }

    private boolean canCreateMeeting(){
        // 1- Vérfier tous les champs sont remplis

        if(binding.inputSubject.getText().toString().isEmpty()){
            Toast.makeText(this,"*Sujet Obligatoire",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(binding.spinnerRoom.getAdapter().isEmpty()){
            Toast.makeText(this,"*Choix salle obligatoire",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(binding.inputPeople.getText().toString().isEmpty()){
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
// 2- crée un meeting via l'API
// 3- faire un PressBack automatique pour revenir sur l'écran principal