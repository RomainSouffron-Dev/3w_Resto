package com.a3wa.a3wresto.a3wresto.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a3wa.a3wresto.a3wresto.Manager.WSManager;
import com.a3wa.a3wresto.a3wresto.Manager.WsListener;
import com.a3wa.a3wresto.a3wresto.Model.User;
import com.a3wa.a3wresto.a3wresto.R;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;

public class CompteActivity extends AppCompatActivity implements View.OnClickListener,  WsListener{

    private EditText etNom;
    private EditText etPrenom;
    private EditText etEmail;
    private EditText etMotDePasse;
    private Button btnCompte;
    private WsListener wsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);

        this.etNom = findViewById(R.id.etNom);
        this.etPrenom = findViewById(R.id.etPrenom);
        this.etEmail = findViewById(R.id.etEmail);
        this.etMotDePasse = findViewById(R.id.etMotDePasse);
        this.btnCompte = findViewById(R.id.btnCompte);

        btnCompte.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (btnCompte == v){
            HashMap param = new HashMap<>();
            param.put("nom", etNom.getText().toString());
            param.put("prenom", etPrenom.getText().toString());
            param.put("login", etEmail.getText().toString());
            param.put("pass", etMotDePasse.getText().toString());

            WSManager manager = new WSManager(this);

            manager.sendRequest(1, "/ws/resto/addCompte", param);
        }


        }



    @Override
    public void errorRequest(int idRequest) {

        Toast.makeText(this, "Erreur" + idRequest, Toast.LENGTH_LONG).show();

    }

    @Override
    public void successRequest(int idRequest, String data) {

        try {

            //convertir data en JSON
            JSONObject obj = new JSONObject(data);
            //serveur rep : 1- succes : id, nom, prenom, email  2- error
            if(!obj.isNull("id")){
                User user = new User(obj.getInt("id"),obj.getString("nom"),obj.getString("prenom"),obj.getString("email"));

                //save sharePref
                getSharedPreferences("USER_INFOS", MODE_PRIVATE).edit().putInt("id",user.getId()).putString("nom", user.getNom()).putString("prenom",user.getPrenom()).putString("email",user.getEmail()).commit();

                startActivity(new Intent(this,RecetteActivity.class)); // page list de recette

            }else{
                Toast.makeText(this, obj.getString("error"), Toast.LENGTH_LONG).show();

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
