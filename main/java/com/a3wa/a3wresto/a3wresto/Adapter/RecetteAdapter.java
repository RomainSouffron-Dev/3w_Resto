package com.a3wa.a3wresto.a3wresto.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.a3wa.a3wresto.a3wresto.Model.Recette;
import com.a3wa.a3wresto.a3wresto.R;
import com.a3wa.a3wresto.a3wresto.ViewHolder.RecetteViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecetteAdapter extends ArrayAdapter<Recette> {

    public RecetteAdapter(Context context, List<Recette> list){
        super(context, 0, list);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_recette, parent, false);
        }

        RecetteViewHolder viewHolder = (RecetteViewHolder) convertView.getTag();

        if(viewHolder == null){
            viewHolder  = new RecetteViewHolder(convertView);

        }

        Recette recette = getItem(position);
        Picasso.get().load(recette.getPhoto()).into(viewHolder.getImgRecet());
        viewHolder.getTitre().setText(recette.getTitle());


        return convertView;
    }
}
