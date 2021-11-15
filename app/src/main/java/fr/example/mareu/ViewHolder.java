package fr.example.mareu;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageViewId;
    public TextView titleReunion;
    public TextView itemViewMail;
    public ImageView deleteButton;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        imageViewId = itemView.findViewById(R.id.imageViewId);
        titleReunion = itemView.findViewById(R.id.title_reunion);
        itemViewMail  = itemView.findViewById(R.id.itemView_mail);
        deleteButton  = itemView.findViewById(R.id.delete_button_itemView);
    }

    public ImageView getImageViewId() {
        return imageViewId;
    }

    public TextView getTitleReunion() {
        return titleReunion;
    }

    public TextView getItemViewMail() {
        return itemViewMail;
    }

    public void setDeleteButton(int resId) {
        deleteButton.setImageResource(resId);
    }
}