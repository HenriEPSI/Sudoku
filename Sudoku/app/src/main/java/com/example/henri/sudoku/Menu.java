package com.example.henri.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity implements View.OnClickListener {

    private Button Lvl1;
    private Button Lvl2;
    private Button Lvl3;
    private int btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Lvl1 = findViewById(R.id.btnLvl1);
        Lvl1.setOnClickListener(this);

        Lvl2 = findViewById(R.id.btnLvl2);
        Lvl2.setOnClickListener(this);

        Lvl3 = findViewById(R.id.btnLvl3);
        Lvl3.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        // Lancer la deuxième activitée
        Intent intent = new Intent(this, ChoixGrille.class);

        //on passe le niveau choisi à l'autre acctivitée
        switch (v.getId())
        {
            case 2131165219 :
            {
                intent.putExtra("Lvl","1");
            }
            break;
            case 2131165220 :
            {
                intent.putExtra("Lvl","2");
            }
            break;
            case 2131165221 :
            {
                intent.putExtra("Lvl","3");
            }
            break;
        }

        //on passe à l'activitée ChoixGrille
        startActivity(intent);
    }
}
