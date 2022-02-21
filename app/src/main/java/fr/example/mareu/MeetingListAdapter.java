package fr.example.mareu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import fr.example.mareu.databinding.ActivityMainItemBinding;
import fr.example.mareu.event.DeleteMeetingEvent;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Workmate;

public class MeetingListAdapter extends RecyclerView.Adapter<MeetingListViewHolder> {

    private List<Meeting> mListMeeting;
    private Context mContext;

    public MeetingListAdapter(List<Meeting> listMeeting, Context context) {
        mListMeeting = listMeeting;
        mContext = context;
    }

    @NonNull
    public MeetingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ActivityMainItemBinding binding = ActivityMainItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MeetingListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingListViewHolder holder, int position) {
        Meeting meeting = mListMeeting.get(position);
        List<Workmate> workmateList = meeting.getParticipants();
        StringBuilder workmateString = new StringBuilder();

        for (Workmate w : workmateList) {
            workmateString.append(w.getEmail()).append(", ");
        }

        StringBuilder titleString = new StringBuilder();
        titleString.append(meeting.getSubject());
        titleString.append(" - ");
        titleString.append(meeting.getFormattedDate());
        titleString.append(" - ");
        titleString.append(mContext.getString(meeting.getRoom().getNameRes()));

        holder.binding.txtTitleMeeting.setText(titleString);
        holder.binding.txtMailList.setText(workmateString);
        holder.binding.imgAvatar.setImageResource(meeting.getRoom().iconRes);
        holder.binding.imgDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListMeeting.size();
    }
}



























