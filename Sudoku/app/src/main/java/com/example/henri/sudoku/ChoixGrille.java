package com.example.henri.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChoixGrille extends AppCompatActivity {

    private ListView listeGrille;
    private String[] listeG = new String[100];
    private String lvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_grille);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lvl = extras.getString("Lvl");
        }

        for(int i=0; i<100; i++)
        {
            listeG[i] = "Grille "+(i+1);
        }

        listeGrille = findViewById(R.id.list_item);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChoixGrille.this,
        android.R.layout.simple_list_item_2,android.R.id.text1, listeG)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                text1.setText(listeG[position]);
                text2.setText("Niveau "+lvl);
                return view;
            }
        };

        listeGrille.setAdapter(adapter);

        listeGrille.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //position commence à 0
                Log.d("MyApp",""+position);

                // Lancer l'activitée résolution
                Intent intent = new Intent(ChoixGrille.this, Resolution.class);
                startActivity(intent);
            }
        });
    }
}