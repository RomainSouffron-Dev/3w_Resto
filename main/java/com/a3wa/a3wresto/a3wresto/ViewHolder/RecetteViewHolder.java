package com.a3wa.a3wresto.a3wresto.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3wa.a3wresto.a3wresto.R;

public class RecetteViewHolder {
    private TextView titre;
    private ImageView imgRecet;
    private ImageView starOn;
    private ImageView starOff;
    private ImageView like;

    public RecetteViewHolder(View v){
        this.titre = v.findViewById(R.id.titre);
        this.imgRecet = v.findViewById(R.id.imgRecet);
        this.starOn = v.findViewById(R.id.starOn);
        this.starOff = v.findViewById(R.id.starOff);
        this.like = v.findViewById(R.id.like);
    }

    public TextView getTitre() {
        return titre;
    }

    public ImageView getImgRecet() {
        return imgRecet;
    }

    public ImageView getStarOn() {
        return starOn;
    }

    public ImageView getStarOff() {
        return starOff;
    }

    public ImageView getLike() {
        return like;
    }
}
