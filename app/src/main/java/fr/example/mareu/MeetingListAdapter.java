package fr.example.mareu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import fr.example.mareu.model.Meeting;

public class MeetingListAdapter {

    private List<Meeting> mListMeeting;

    public MeetingListAdapter(List<Meeting> listMeeting) {
        mListMeeting = listMeeting;
    }

    private FloatingActionButton deleteButton;



    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main, parent, false);
        return new RecyclerView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
       Meeting meeting = mListMeeting.get(position);

        viewHolder.setDrawable(meeting.getRoom().getColorRes());

        viewHolder.getMeetingList()
                .setText(meeting.getSubject() + " - " + meeting.getRoom().getNameRes());

        viewHolder.getMeetingWorkmate()
                .setText(meeting.getParticipants());

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting)); holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post(new DeleteMeetingrEvent(meeting));
                    }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mListMeeting.size(); }
}







