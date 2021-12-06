package fr.example.mareu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobParameters;
import android.os.Bundle;
import android.os.Message;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import javax.security.auth.Subject;

import fr.example.mareu.databinding.ActivityCreateMeetingBinding;

public class CreateMeetingActivity extends AppCompatActivity {

    private ActivityCreateMeetingBinding binding;
    private JobParameters jobParameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.inputSubject.getText().toString();
        binding.inputPeople.getText().toString();
        binding.inputRoom.getText().toString();
        binding.inputTime.getText().toString();
    }
    if ((inputSubject.getText().toString().compareTo("") == 0)||
            (inputPeople.getText().toString().compareTo("") == 0)||)
            (inputRoom.getText().toString().compareTo("") == 0)||)
            (inputTime.getText().toString().compareTo("") == 0)) {

    JobParameters.showMessageDialog(false, " *Champ obligatoire");
    } else {  jobParameters.showMessageDialog(true, "");
    }
}