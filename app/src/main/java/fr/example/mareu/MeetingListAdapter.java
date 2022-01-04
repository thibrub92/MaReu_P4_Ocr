package fr.example.mareu;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;
import fr.example.mareu.event.DeleteMeetingEvent;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;
import fr.example.mareu.model.Workmate;

public class MeetingListAdapter extends RecyclerView.Adapter<MeetingListViewHolder> {

    private List<Meeting> mListMeeting;
    private Context mContext;
    private List<Room> filterRooms = new ArrayList<>();
    private boolean[] roomFiltersSelected = new boolean[8];
  //  private final MeetingListAdapter meetingListAdapter;

    public MeetingListAdapter(List<Meeting> listMeeting, Context context) {
        mListMeeting = listMeeting;
        mContext = context;
        filterRooms = filterRooms;
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
        List<Workmate> workmateList = meeting.getParticipants();
        StringBuilder workmateString = new StringBuilder();

        for (Workmate w : workmateList) {
            workmateString.append(w.getEmail()).append(" , ");
        }

        StringBuilder titleString = new StringBuilder();
        titleString.append(meeting.getSubject());
        titleString.append(" - ");
        titleString.append(meeting.getFormattedDate());
        titleString.append(" - ");
        titleString.append(mContext.getString(meeting.getRoom().getNameRes()));

        holder.titleReunion.setText(titleString);
        holder.itemViewMail.setText(workmateString);
        holder.imageViewId.setImageResource(meeting.getRoom().iconRes);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting)); }
        });
    }

    @Override
    public int getItemCount() {
        return mListMeeting.size();
    }
}



























