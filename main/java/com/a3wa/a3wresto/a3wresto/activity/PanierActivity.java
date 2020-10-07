package com.a3wa.a3wresto.a3wresto.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.a3wa.a3wresto.a3wresto.Adapter.RecetteAdapter;
import com.a3wa.a3wresto.a3wresto.Manager.FavoriManager;
import com.a3wa.a3wresto.a3wresto.Manager.PanierManager;
import com.a3wa.a3wresto.a3wresto.Manager.WSManager;
import com.a3wa.a3wresto.a3wresto.Manager.WsListener;
import com.a3wa.a3wresto.a3wresto.Model.Panier;
import com.a3wa.a3wresto.a3wresto.Model.Recette;
import com.a3wa.a3wresto.a3wresto.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanierActivity extends AppCompatActivity implements WsListener, AdapterView.OnItemClickListener{

    private ListView listView;
    private List<Recette> panierList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        this.listView = findViewById(R.id.listView);

        PanierManager manager = new PanierManager(this);
        List<Panier> panierList = manager.getAll();

        String result = "";
        for (Panier tmp: panierList) {
            result += tmp.getId()+":"+tmp.getQte() + " ";
        }


        WSManager wsManager = new WSManager(this);

        HashMap<String, String> parametre = new HashMap<>();
        parametre.put("produits", result);
        wsManager.sendRequest(1, "/ws/resto/panier", parametre);

    }

    @Override
    public void errorRequest(int idRequest) {

    }

    @Override
    public void successRequest(int idRequest, String data) {


        panierList = new ArrayList<>();
        try {

            JSONObject json = new JSONObject(data);

            JSONArray array = json.getJSONArray("panier");

            for (int i = 0; i < array.length(); i++) {
                // pour parcourir le tableau
                JSONObject obj = array.getJSONObject(i);
                Recette recette = new Recette(obj.getInt("id"), obj.getString("title"), obj.getString("photo"), obj.getInt("note"));
                panierList.add(recette);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecetteAdapter adapter = new RecetteAdapter(this, this.panierList);
        this.listView.setAdapter(adapter);
        this.listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Voulez-vous supprimer")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        PanierManager manager = new PanierManager(PanierActivity.this);
                        manager.delete (panierList.get(position).getId());

                        panierList = new ArrayList<>();


                        List<Panier> panierList = manager.getAll();

                        String result = "";
                        for (Panier tmp: panierList) {
                            result += tmp.getId()+":"+tmp.getQte() + " ";
                        }


                        WSManager wsManager = new WSManager(PanierActivity.this);

                        HashMap<String, String> parametre = new HashMap<>();
                        parametre.put("produits", result);
                        wsManager.sendRequest(1, "/ws/resto/panier", parametre);
                    }
                })

                .setNegativeButton("cancel", null);


        AlertDialog alertDialog = builder.create();

        alertDialog.show();



    }
}
