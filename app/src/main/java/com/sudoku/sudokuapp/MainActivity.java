package com.sudoku.sudokuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final private String[] nums = {" ","1","2","3","4","5","6","7","8","9"};
    private Spinner[][] cells = new Spinner[9][9];
    private ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nums);

        TableLayout tLayout = findViewById(R.id.tableLayout);
        // Bucle de omplir totes les cel·les del sudoku
        for (int i = 0; i < 9; i++){
            TableRow row = new TableRow(this);
            for (int y = 0; y < 9;y++){
                cells[i][y] = new Spinner(this);
                cells[i][y].setAdapter(spinnerAdapter);
                cells[i][y].setBackground(null);

                // Per definir els quadrants deixem un padding inferior a la i
                if(i == 2 || i == 5){
                    cells[i][y].setPadding(0,0,0,100);
                } else {
                    cells[i][y].setPadding(0,0,0,0);
                }

                // Per definir els qudrants finalment deixem un padding dret a la y
                if (y == 2||y==5){
                    cells[i][y].setPadding(cells[i][y].getPaddingLeft(), cells[i][y].getPaddingTop(), 100, cells[i][y].getPaddingBottom());
                }

                cells[i][y].setSelection((int) (Math.random() * nums.length));
                row.addView(cells[i][y]);
            }
            tLayout.addView(row);
        }


    }
}