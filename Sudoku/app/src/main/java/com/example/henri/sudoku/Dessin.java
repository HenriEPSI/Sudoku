package com.example.henri.sudoku;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Henri on 15/03/2018.
 */

public class Dessin extends View implements View.OnTouchListener {

    private Paint paint = new Paint();
    private Paint paint2 = new Paint();
    private int startX;
    private int startY;
    private int stopX;
    private int stopY ;
    private int TstartX;
    private int TstartY;
    private int TstopX;
    private int TstopY;
    private String grilleSudoku;
    private String grilleSudokuSolution;
    private String[][] grilleSudoku2 = new  String[9][9];
    private int nombre = 0;

    public Dessin(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);

        grilleSudoku = "43 6    97   24 1     3   59    58   6     9   29    41   9     4 58   68    2 41";
        //grilleSudoku = "43165728979582461362813947591734586256421839738297615417649352824958173685376294 ";       //test de la vérification
        grilleSudokuSolution = "431657289795824613628139475917345862564218397382976154176493528249581736853762941";
        Log.d("grilleSudoku", " " + grilleSudoku.length());
        Log.d("grilleSudokuSolution", " " + grilleSudokuSolution.length());

        int pos =0;
        //ici on transforme la suite des valeurs du sudoku en un tableau à double entrée
        for(int i=0; i< 9; i++)
        {
            for(int j=0; j< 9; j++)
            {
                if(j==8)
                {
                    pos = pos + 9;
                    grilleSudoku2[i][j] = Character.toString(grilleSudoku.charAt(pos-1));
                }
                else
                {
                    grilleSudoku2[i][j] = Character.toString(grilleSudoku.charAt(pos+j));
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.RED);
        paint2.setColor(Color.BLACK);

        for(int i=0; i<9; i++)
        {
            stopX = startX;
            startY = 0;
            stopY = getWidth();

            if(i == 3 || i == 6)
            {
                paint.setStrokeWidth(5);
            }
            else
            {
                paint.setStrokeWidth(2);
            }
            paint2.setTextSize(30);
            paint2.setColor(Color.BLUE);
            paint2.setStyle(Paint.Style.FILL);

            //dessin des chiffres dans toutes les cases
            for(int j=0; j<9; j++)
            {
                canvas.drawText(grilleSudoku2[j][i], startX+getWidth()/21, startY+paint2.getTextSize()/2+getWidth()/19+ (getWidth()/9)*j, paint2);
            }

            //dessin des lignes verticales
            canvas.drawLine(startX, startY, stopX, stopY, paint);
            startX = startX + getWidth()/9;
        }

        for(int i=0; i<=9; i++)
        {
            startX = 0;
            stopY = startY;
            stopX = getWidth();

            if(i == 3 || i == 6)
            {
                paint.setStrokeWidth(5);
            }
            else
            {
                paint.setStrokeWidth(2);
            }

            //dessin des lignes hotizontales
            canvas.drawLine(startX, startY, stopX, stopY, paint);
            startY = startY + getWidth()/9;
        }

        Boolean color = true;
        for(int i=0; i<9; i++)
        {
            Paint paint3 = new Paint();

            paint3.setTextSize(30);
            paint3.setColor(Color.GREEN);
            //paint3.setStyle(Paint.Style.FILL);
            if (color)
            {
                canvas.drawRect(0+i*getWidth()/9, getWidth()+getWidth()/18, getWidth()/9+i*getWidth()/9, getWidth()+getWidth()/18+getWidth()/9, paint);
                color = false;
            }
            else
            {
                canvas.drawRect(0+i*getWidth()/9, getWidth()+getWidth()/18, getWidth()/9+i*getWidth()/9, getWidth()+getWidth()/18+getWidth()/9, paint3);
                color = true;
            }

            canvas.drawText(Integer.toString(i+1),getWidth()/21+i*getWidth()/9, getWidth()+paint2.getTextSize()/2+2*getWidth()/19, paint2);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if((int)event.getY() > getWidth())
                {
                    //boutons
                    for(int i=0; i<=9; i++)
                    {
                        if ((int)event.getX() > i*getWidth()/9 && (int)event.getY() > getWidth()+getWidth()/18 && (int)event.getX() < (i+1)*getWidth()/9 && (int)event.getY() < getWidth()+getWidth()/18+getWidth()/9)
                        {
                            Log.d("case bouton"," "+(i+1));
                            nombre = i+1;
                            break;
                        }
                    }
                    Log.d("ACTION_DOWN"," "+nombre);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("ACTION_MOVE"," ");
                break;
            case MotionEvent.ACTION_UP:
                if((int)event.getY() < getWidth() && nombre != 0)
                {
                    Log.d("ACTION_UP"," "+(int)event.getX()+" "+ (int)event.getY());
                    getCase((int)event.getX(), (int)event.getY());
                    //on rafraichie la view
                    verifier();
                    v.invalidate();
                }
                break;
        }

        return true;
    }

    @SuppressLint("WrongCall")
    public void getCase(int x, int y)
    {
        boolean quitter = false;
        TstartX = 0;
        TstartY = -getWidth()/9;
        TstopX = getWidth()/9;
        TstopY = 0;

        for(int j=0; j<=8; j++)
        {
            TstartY = TstartY + getWidth()/9;
            TstopY = TstopY + getWidth()/9;

            for(int i=0; i<=8; i++)
            {
                if (x > TstartX && y > TstartY && x < TstopX && y < TstopY)
                {
                    if(nombre != 0)
                    {
                        Log.d("case "," "+(i)+" "+(j));
                        grilleSudoku2[j][i] = Integer.toString(nombre);
                        nombre = 0;

                    }
                    quitter = true;
                    break;
                }

                //case suivante
                TstartX = TstartX + getWidth()/9;
                TstopX = TstopX + getWidth()/9;
            }

            if (quitter)
            {
                break;
            }
            //on repard de zéro
            TstartX = 0;
            TstopX = getWidth()/9;
        }
    }

    public void verifier()
    {
        int total=0;
        int pos = 0;
        for(int i = 0; i <= 8; i++)
        {
            for(int j = 0; j <= 8; j++)
            {
                if(j==8)
                {
                    pos = pos + 9;

                    if(grilleSudoku2[i][j].equals(Character.toString(grilleSudokuSolution.charAt( pos - 1 ))))
                    {
                        total = total + 1;
                    }
                }
                else
                {
                    if(grilleSudoku2[i][j].equals(Character.toString(grilleSudokuSolution.charAt( pos + j ))))
                    {
                        total = total + 1;
                    }
                }
            }
        }

        if(total == 81)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Bravo !");
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder.create();
            alert11.show();
        }
    }
}