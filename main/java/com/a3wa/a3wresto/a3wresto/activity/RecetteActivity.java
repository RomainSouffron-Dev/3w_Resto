package com.a3wa.a3wresto.a3wresto.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.a3wa.a3wresto.a3wresto.Adapter.RecetteAdapter;
import com.a3wa.a3wresto.a3wresto.Manager.WSManager;
import com.a3wa.a3wresto.a3wresto.Manager.WsListener;
import com.a3wa.a3wresto.a3wresto.Model.Recette;
import com.a3wa.a3wresto.a3wresto.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecetteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, WsListener {

    private ListView listView;
    private List<Recette> recetteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette);

        this.listView = findViewById(R.id.listView);
        this.recetteList = new ArrayList<>();
        this.listView.setOnItemClickListener(this);

        WSManager manager = new WSManager(this);

        String target;
        target = "/ws/resto/listRecettes";

        manager.sendRequest(1, target, null);

    }


    @Override
    public void errorRequest(int idRequest) {

    }

    @Override
    public void successRequest(int idRequest, String data) {

        try {
            JSONArray array = new JSONArray(data);

            for (int i = 0; i <array.length() ; i++) {
                // pour parcourir le tableau
                JSONObject obj = array.getJSONObject(i);
                Recette recette = new Recette(obj.getInt("id"),obj.getString("title"),obj.getString("photo"),obj.getInt("note"));
                recetteList.add(recette);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecetteAdapter adapter = new RecetteAdapter(this, this.recetteList);
        this.listView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, InfoRecetteActivity.class);

        intent.putExtra("recette", this.recetteList.get(position));

        startActivity(intent);

    }

}
