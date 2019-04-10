package com.example.premiere_appli_test;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class page_1 extends AppCompatActivity implements View.OnClickListener {
    String question;
    String rep1,rep2,rep3,rep4;
    Integer vrai_rep, niveau, niveau_max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_1);
        Button btrep1 = findViewById(R.id.btrep1);
        Button btrep2 = findViewById(R.id.btrep2);
        Button btrep3 = findViewById(R.id.btrep3);
        Button btrep4 = findViewById(R.id.btrep4);
        Button bouton_setting = findViewById(R.id.bouton_setting);
        Button bouton_retour = findViewById(R.id.bouton_retour);
        TextView niveau_question = findViewById(R.id.niveau_question);
        Button indice = findViewById(R.id.indice);
        btrep1.setOnClickListener(this); btrep2.setOnClickListener(this); btrep3.setOnClickListener(this); btrep4.setOnClickListener(this);
        bouton_retour.setOnClickListener(this); bouton_setting.setOnClickListener(this); indice.setOnClickListener(this);

        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        niveau = pref.getInt("niveau", 0);
        niveau_question.setText("QUESTION N°"+niveau.toString());

        afficherJSON();

    }

    public String loadJSON(){
        String json = null;
        try {
            InputStream is = page_1.this.getAssets().open("document.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex){
            ex.printStackTrace();
            return null;
        } return json;
    }

    public void afficherJSON(){
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(loadJSON());
            JSONObject obj = jsonArray.getJSONObject(niveau);
            question = obj.getString("question");
            rep1 = obj.getString("rep1"); rep2 = obj.getString("rep2");
            rep3 = obj.getString("rep3"); rep4 = obj.getString("rep4");
            vrai_rep = obj.getInt("vrai");
            
            if (question.equals("fin")) Toast.makeText(this, "STOP", Toast.LENGTH_SHORT).show();
            
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void gagne(){
        Toast.makeText(this, "GAGNE", Toast.LENGTH_SHORT).show();
        niveau = niveau+1;
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("niveau", niveau);
        editor.apply();
        startActivity(new Intent(page_1.this, page_1.class));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btrep1) {
            if (vrai_rep == 1) gagne();
            else Toast.makeText(this, "perdu :(", Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.btrep2) {
            if (vrai_rep == 2) gagne();
            else Toast.makeText(this, "perdu :(", Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.btrep3) {
            if (vrai_rep == 3) gagne();
            else Toast.makeText(this, "perdu :(", Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.btrep4) {
            if (vrai_rep == 4) gagne();
            else Toast.makeText(this, "perdu :(", Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.bouton_retour) {
            Intent intent = new Intent(page_1.this,MainActivity.class);
            startActivity(intent);        }
        if (v.getId() == R.id.bouton_setting) {
            DialogPerso.showDialog(this, "Voici les règles du jeu dans une Alertdialog personnalisée");
        }
        if (v.getId() == R.id.indice){
            niveau = 0;
            SharedPreferences pref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("niveau", niveau);
            editor.apply();
            startActivity(new Intent(page_1.this, page_1.class));
        }
    }
}
