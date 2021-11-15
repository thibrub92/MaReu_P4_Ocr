package fr.example.mareu;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

import fr.example.mareu.model.Meeting;

public class MeetingListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

private List<Meeting> mListMeeting;

public FloatingActionButton mDeleteButton;


// constructeur de l'adapteur
public MeetingListAdapter (List<Meeting> listMeeting){

    mListMeeting = listMeeting;
}

    public void updateReunions(@NonNull final List<Meeting> meetings) {
        this.mListMeeting = meetings;
}
//Crée les vues appelées par le layout manager

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(view);
}

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
     Meeting meeting = mListMeeting.get(position);
     holder.itemView(mListMeeting[position]);
}

    @Override
    public int getItemCount() {
        return mListMeeting.size();
    }
}



















}











