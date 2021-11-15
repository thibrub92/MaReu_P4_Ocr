package fr.example.mareu;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import fr.example.mareu.model.Meeting;

public class MeetingListAdapter extends RecyclerView.Adapter<MeetingListViewHolder> {

    private List<Meeting> mListMeeting;

    public MeetingListAdapter (List<Meeting> listMeeting){
        mListMeeting = listMeeting;
    }

    @NonNull
    public MeetingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_item, parent, false);
        return new MeetingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingListViewHolder holder, int position) {
        Meeting meeting = mListMeeting.get(position);
      //  holder.itemView(mListMeeting[position]);
    }

    @Override
    public int getItemCount() {
        return mListMeeting.size();
    }
}


























