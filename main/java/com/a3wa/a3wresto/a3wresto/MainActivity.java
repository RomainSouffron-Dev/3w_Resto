package com.a3wa.a3wresto.a3wresto;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.a3wa.a3wresto.a3wresto.Model.Recette;
import com.a3wa.a3wresto.a3wresto.activity.HomeActivity;
import com.a3wa.a3wresto.a3wresto.activity.RecetteActivity;

public class MainActivity extends AppCompatActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Handler handler = new Handler();
        handler.postDelayed(this, 1000);
    }

    @Override
    public void run() {

        // test si l'utilisateur est déja connecter

        int idUser = getSharedPreferences("USER_INFOS", MODE_PRIVATE).getInt("id",-1);

        if(idUser == -1){ // l'id ne se trouve pas dans le getSharedPreferences
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, RecetteActivity.class);
            startActivity(intent);
        }


    }
}
