package com.sudoku.sudokuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final private String[] nums = {" ","1","2","3","4","5","6","7","8","9"};
    private Spinner[][] cells = new Spinner[9][9];
    private ArrayAdapter<String> spinnerAdapter;
    private boolean firstStart = true;
    private int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nums);

        TableLayout tLayout = findViewById(R.id.tableLayout);
        // Bucle de omplir totes les cel·les del sudoku
        for (int i = 0; i < 9; i++){
            TableRow row = new TableRow(this);
            for (int y = 0; y < 9; y++){
                int posI = i;
                int posY = y;
                cells[i][y] = new Spinner(this);
                cells[i][y].setAdapter(spinnerAdapter);
                cells[i][y].setBackground(null);
                if ((i%2==0 && y%2==0) || (i%2!=0 && y%2!=0)){
                    cells[i][y].setBackgroundColor(Color.parseColor("#BDFCEB"));
                } else {
                    cells[i][y].setBackgroundColor(Color.parseColor("#FFFFFF"));
                }

                cells[i][y].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        boolean grid = checkSudokuGrid(posI, posY, position);
                        boolean horitzontal = checkHoritzontal(posI, posY, position);
                        boolean vertical = checkVertical(posI, posY, position);
                        // Si cualquiera de las funciones devuelve false, se borra el numero porque esta repetido
                        if (!grid || !horitzontal || !vertical){
                            cells[posI][posY].setSelection(0);
                        } else {
                            boolean finish = finishCheck();
                            if(finish){
                                Log.d("FINISH", "ACABADO");
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                // Per definir els quadrants deixem un padding inferior a la i
                if(i == 2 || i == 5){
                    cells[i][y].setPadding(0,0,0,100);
                } else {
                    cells[i][y].setPadding(0,0,0,0);
                }

                // Per definir els quadrants finalment deixem un padding dret a la y
                if (y == 2||y==5) {
                    cells[i][y].setPadding(cells[i][y].getPaddingLeft(), cells[i][y].getPaddingTop(), 100, cells[i][y].getPaddingBottom());
                }


                row.addView(cells[i][y]);
            }
            tLayout.addView(row);
        }

        while (cont < 16){
            int i = (int) (Math.random() * nums.length - 1);
            int y = (int) (Math.random() * nums.length - 1);
            if(cells[i][y].getSelectedItem().toString().equals(" ")){
                cells[i][y].setSelection((int) (Math.random() * nums.length - 1)+1);
                cont++;
            }
        }
    }

    public boolean checkSudokuGrid(int i, int y, int value){

        // Primer cuadrante
        if (i < 3 && y < 3){
            for (int x = 0; x < 3; x++){
                for (int z = 0; z < 3;z++){
                    if (x != i || z != y){
                        if(!cells[x][z].getSelectedItem().toString().equals(" ") && Integer.parseInt(cells[x][z].getSelectedItem().toString()) == value){
                            return  false;
                        }
                    }
                }
            }
        } /* Segundo cuadrante */else if (i < 3 && y > 2 && y < 6){
            for (int x = 0; x < 3; x++){
                for (int z = 3; z < 6;z++){
                    if (x != i || z != y){
                        if(!cells[x][z].getSelectedItem().toString().equals(" ") && Integer.parseInt(cells[x][z].getSelectedItem().toString()) == value){
                            return  false;
                        }
                    }
                }
            }
        } /* Tercer cuadrante */else if (i < 3 && y > 5){
            for (int x = 0; x < 3; x++){
                for (int z = 6; z < 9;z++){
                    if (x != i || z != y){
                        if(!cells[x][z].getSelectedItem().toString().equals(" ") && Integer.parseInt(cells[x][z].getSelectedItem().toString()) == value){
                            return  false;
                        }
                    }
                }
            }
        } /* Cuarto cuadrante */else if (i > 2 && i < 6 && y < 3){
            for (int x = 3; x < 6; x++){
                for (int z = 0; z < 3;z++){
                    if (x != i || z != y){
                        if(!cells[x][z].getSelectedItem().toString().equals(" ") && Integer.parseInt(cells[x][z].getSelectedItem().toString()) == value){
                            return  false;
                        }
                    }
                }
            }
        } /* Quinto cuadrante */else if (i > 2 && i < 6 && y > 2 && y < 6){
            for (int x = 3; x < 6; x++){
                for (int z = 3; z < 6;z++){
                    if (x != i || z != y){
                        if(!cells[x][z].getSelectedItem().toString().equals(" ") && Integer.parseInt(cells[x][z].getSelectedItem().toString()) == value){
                            return  false;
                        }
                    }
                }
            }
        } /* Sexto cuadrante */ else if (i > 2 && i < 6 && y > 5 && y < 9){
            for (int x = 3; x < 6; x++){
                for (int z = 6; z < 9;z++){
                    if (x != i || z != y){
                        if(!cells[x][z].getSelectedItem().toString().equals(" ") && Integer.parseInt(cells[x][z].getSelectedItem().toString()) == value){
                            return  false;
                        }
                    }
                }
            }
        }/* Septimo cuadrante */ else if (i > 5 && i < 9 && y < 3){
            for (int x = 6; x < 9; x++){
                for (int z = 0; z < 3;z++){
                    if (x != i || z != y){
                        if(!cells[x][z].getSelectedItem().toString().equals(" ") && Integer.parseInt(cells[x][z].getSelectedItem().toString()) == value){
                            return  false;
                        }
                    }
                }
            }
        } /* Octavo cuadrante */else if (i > 5 && i < 9 && y > 2 && y < 6){
            for (int x = 6; x < 9; x++){
                for (int z = 3; z < 6;z++){
                    if (x != i || z != y){
                        if(!cells[x][z].getSelectedItem().toString().equals(" ") && Integer.parseInt(cells[x][z].getSelectedItem().toString()) == value){
                            return  false;
                        }
                    }
                }
            }
        } /* Noveno cuadrante */else if (i > 5 && i < 9 && y > 5 && y < 9){
            for (int x = 6; x < 9; x++){
                for (int z = 6; z < 9;z++){
                    if (x != i || z != y){
                        if(!cells[x][z].getSelectedItem().toString().equals(" ") && Integer.parseInt(cells[x][z].getSelectedItem().toString()) == value){
                            return  false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean checkHoritzontal(int i, int y, int value){

        for (int z = 0; z < 9; z++){
            if (z!=y){
                if(!cells[i][z].getSelectedItem().toString().equals(" ") && Integer.parseInt(cells[i][z].getSelectedItem().toString()) == value){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkVertical(int i, int y, int value){

        for (int x = 0; x < 9; x++){
            if (x!=i){
                if(!cells[x][y].getSelectedItem().toString().equals(" ") && Integer.parseInt(cells[x][y].getSelectedItem().toString()) == value){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean finishCheck(){

        for (int i = 0; i < 9; i++){
            for (int y = 0; y < 9; y++){
                if (cells[i][y].getSelectedItem().toString().equals(" ")){
                    return false;
                }
            }
        }

        return true;
    }

}