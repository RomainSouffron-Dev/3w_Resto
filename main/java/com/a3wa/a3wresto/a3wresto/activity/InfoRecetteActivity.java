package com.a3wa.a3wresto.a3wresto.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3wa.a3wresto.a3wresto.Fragments.MapActivity;
import com.a3wa.a3wresto.a3wresto.Manager.FavoriManager;
import com.a3wa.a3wresto.a3wresto.Manager.PanierManager;
import com.a3wa.a3wresto.a3wresto.Manager.WSManager;
import com.a3wa.a3wresto.a3wresto.Manager.WsListener;
import com.a3wa.a3wresto.a3wresto.Model.Panier;
import com.a3wa.a3wresto.a3wresto.Model.Recette;
import com.a3wa.a3wresto.a3wresto.Model.Restaurant;
import com.a3wa.a3wresto.a3wresto.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class InfoRecetteActivity extends AppCompatActivity implements WsListener, View.OnClickListener {

    private Recette recette;

    private ImageView photo;
    private TextView title;
    private ImageView starOn;
    private ImageView starOff;
    private ImageView like;
    private TextView tempsPreparation;
    private TextView tempsCookRime;
    private TextView calorie;
    private TextView ingredient;
    private TextView instruction;


    private ImageView photo2;
    private TextView nom;
    private TextView tel;
    private TextView email;
    private TextView description;
    private TextView adresse;
    private TextView ville;
    private TextView cp;
    private Button btnMap;

    private Button btnFav;

    private Button btnPan;

    private TextView panQte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_recette);

        this.photo = findViewById(R.id.photo);
        this.title = findViewById(R.id.title);
        this.starOn = findViewById(R.id.starOn);
        this.starOff = findViewById(R.id.starOff);
        this.like = findViewById(R.id.like);
        this.tempsPreparation = findViewById(R.id.tempsPreparation);
        this.tempsCookRime = findViewById(R.id.tempsCookRime);
        this.calorie = findViewById(R.id.calorie);
        this.ingredient = findViewById(R.id.ingredient);
        this.instruction = findViewById(R.id.instruction);

        this.photo2 = findViewById(R.id.photo2);
        this.nom = findViewById(R.id.nom);
        this.tel = findViewById(R.id.tel);
        this.email = findViewById(R.id.email);
        this.description = findViewById(R.id.description);
        this.adresse = findViewById(R.id.adresse);
        this.ville = findViewById(R.id.ville);
        this.cp = findViewById(R.id.cp);
        this.btnMap = findViewById(R.id.btnMap);
        this.btnFav = findViewById(R.id.btnFav);
        this.btnPan = findViewById(R.id.btnPan);
        this.panQte = findViewById(R.id.panQte);
        this.btnMap.setOnClickListener(this);
        this.btnFav.setOnClickListener(this);
        this.btnPan.setOnClickListener(this);

        Recette recette = (Recette) getIntent().getSerializableExtra("recette");


        Picasso.get().load(recette.getPhoto()).into(photo);



        WSManager manager = new WSManager(this);

        String target;
        target = "/ws/resto/infoRecette/"+recette.getId();

        manager.sendRequest(1, target, null);
    }

    @Override
    public void errorRequest(int idRequest) {

    }

    @Override
    public void successRequest(int idRequest, String data) {

        try {
            JSONObject obj = new JSONObject(data);

            recette = new Recette();

            recette.setId(obj.getInt("id"));
            recette.setTitle(obj.getString("title"));
            recette.setPhoto(obj.getString("photo"));
            recette.setNote(obj.getInt("note"));
            recette.setTempsPreparation(obj.getString("tempsPreparation"));
            recette.setTempsCookRime(obj.getString("tempsCookRime"));
            recette.setCalorie(obj.getString("calories"));
            recette.setIngredients(obj.getString("ingredients"));
            recette.setInstructions(obj.getString("instructions"));

            this.title.setText(recette.getTitle());
            this.tempsPreparation.setText(String.valueOf(recette.getTempsPreparation()));
            this.tempsCookRime.setText(String.valueOf(recette.getTempsCookRime()));
            this.calorie.setText(String.valueOf(recette.getCalorie()));
            this.ingredient.setText(String.valueOf(recette.getIngredients()));
            this.instruction.setText(String.valueOf(recette.getInstructions()));

            JSONObject resto = obj.getJSONObject("restaurant");

            Restaurant restaurant = new Restaurant();

            restaurant.setId(resto.getInt("id"));
            restaurant.setNom(resto.getString("nom"));
            restaurant.setPhoto2(resto.getString("photo"));
            restaurant.setTel(resto.getString("tel"));
            restaurant.setEmail(resto.getString("email"));
            restaurant.setDescription(resto.getString("description"));
            restaurant.setAdresse(resto.getString("adresse"));
            restaurant.setVille(resto.getString("ville"));
            restaurant.setCp(resto.getInt("cp"));
            restaurant.setLat(resto.getDouble("lat"));
            restaurant.setLng(resto.getDouble("lng"));

            this.nom.setText(String.valueOf(restaurant.getNom()));
            this.tel.setText(String.valueOf(restaurant.getTel()));
            this.email.setText(String.valueOf(restaurant.getEmail()));
            this.description.setText(String.valueOf(restaurant.getDescription()));
            this.adresse.setText(String.valueOf(restaurant.getAdresse()));
            this.ville.setText(restaurant.getVille());
            this.cp.setText(String.valueOf(restaurant.getCp()));


            this.recette.setRestaurant(restaurant);

            Picasso.get().load(restaurant.getPhoto2()).into(photo2);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

        if(btnMap == v) {
            Intent intent = new Intent(this, MapActivity.class);
            intent.putExtra("recette", this.recette);
            startActivity(intent);

        }else if(btnFav == v){
            // ajt a la bd
            FavoriManager manager = new FavoriManager(this);
            manager.add(this.recette.getId());

            Intent intent = new Intent(this, FavoriActivity.class);
            intent.putExtra("recette", this.recette);
            startActivity(intent);
        }else if(btnPan == v){
            PanierManager manager = new PanierManager(this);
            Recette recette = (Recette) getIntent().getSerializableExtra("recette");
            // 1- Tester si l'id existe dans le manager (créer une méthode qui s'appel isIdExiste(int id prod)

            Panier panier = manager.getProduct(recette.getId());
            // 3- si l'id existe pas
            //3.1 ajouter a la base manager.add(produit.getId(), 1); 1=>Qte


            if( panier == null){


                manager.add(new Panier(recette.getId(), 1));
                //this.panQte.setText("1");

                // 2- si l'id existe
                //2.1- add qte (delete prod ds la bd + manager.add(produit.getId(), zonetext+1); )(update (id.zonetext+1)  )
            }else{
                manager.delete (recette.getId());
                manager.add(new Panier(recette.getId(), panier.getQte() + 1));

            }




           /* Panier panier = new Panier();
            panier.setId(this.recette.getId());
            panier.setQte(1);*/

            Intent intent = new Intent(this, PanierActivity.class);
            intent.putExtra("recette", this.recette);
            startActivity(intent);
        }

    }
}
