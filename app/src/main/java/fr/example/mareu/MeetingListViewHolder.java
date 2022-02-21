package fr.example.mareu;

import androidx.recyclerview.widget.RecyclerView;

import fr.example.mareu.databinding.ActivityMainItemBinding;

public class MeetingListViewHolder extends RecyclerView.ViewHolder {

    public ActivityMainItemBinding binding;

    public MeetingListViewHolder(ActivityMainItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
