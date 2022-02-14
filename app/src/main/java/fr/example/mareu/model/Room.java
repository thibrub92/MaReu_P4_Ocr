package fr.example.mareu.model;


import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import fr.example.mareu.R;

public enum Room{

    MARIO (R.string.Mario,R.drawable.ic_room_mario,R.color.red),
    LUIGI(R.string.Luigi,R.drawable.ic_room_luigi,R.color.darkGreen),
    PEACH(R.string.Peach,R.drawable.ic_room_peach,R.color.pink),
    DAISY(R.string.Daisy,R.drawable.ic_room_daisy,R.color.orange),
    WARIO(R.string.Wario,R.drawable.ic_room_wario,R.color.purple),
    BOWSER(R.string.Bowser,R.drawable.ic_room_bowser,R.color.orange),
    YOSHI(R.string.Yoshi,R.drawable.ic_room_yoshi,R.color.green),
    GOOMBA(R.string.Goomba,R.drawable.ic_room_goomba,R.color.browne);

    @StringRes
    public int nameRes;
    @DrawableRes
    public int iconRes;
    @ColorRes
    public int colorRes;

    private Room(@StringRes int nameRes, @DrawableRes int iconRes, @ColorRes int colorRes) {
        this.nameRes = nameRes;
        this.iconRes = iconRes;
        this.colorRes = colorRes; }

    @StringRes
    public int getNameRes(){
        return nameRes;
    }

    @DrawableRes
    int getIconRes(){
        return iconRes;
    }

    @ColorRes
    public int getColorRes(){
        return colorRes;
    }
}

