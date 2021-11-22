package fr.example.mareu;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.eventbus.EventBus;

import java.util.List;

import fr.example.mareu.model.Meeting;

public class MeetingListAdapter extends RecyclerView.Adapter<MeetingListViewHolder> {

    private List<Meeting> mListMeeting;

    public MeetingListAdapter(List<Meeting> listMeeting) {
        mListMeeting = listMeeting;
    }

    private ImageView deleteButton;

    @NonNull
    public MeetingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_item, parent, false);
        return new MeetingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingListViewHolder holder, int position) {
        Meeting meeting = mListMeeting.get(position);
        holder.titleReunion.setText("test");                //holder.itemView(mListMeeting[position]);
        holder.itemViewMail.setText("test - test");
        holder.imageViewId.setImageResource(R.drawable.ic_room_mario);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                intent.putExtra("Meeting Room",meeting);
                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mListMeeting.size();
    }
}



























